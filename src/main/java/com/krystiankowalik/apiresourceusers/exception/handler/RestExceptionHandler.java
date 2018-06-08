package com.krystiankowalik.apiresourceusers.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ConnectException.class)
    protected ResponseEntity<Object> handleConnectException(ConnectException e) {
        return buildStandardResponseEntity(e, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherExceptions(Exception e) {
        return buildStandardResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(Exception e) {
        return buildStandardResponseEntity(e, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildStandardResponseEntity(Exception e, HttpStatus httpStatus) {
        ApiError apiError = new ApiError(httpStatus);
        apiError.setMessage(e.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}