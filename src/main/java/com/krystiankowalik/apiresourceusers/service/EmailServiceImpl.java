package com.krystiankowalik.apiresourceusers.service;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import com.krystiankowalik.apiresourceusers.network.ApiEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Service
@AllArgsConstructor
@Log4j2
public class EmailServiceImpl implements EmailService {

    @Autowired
    private ApiEmailService apiEmailService;

    @Override
    public void sendEmail(EmailMessage mailMessage) throws Throwable {

        apiEmailService.sendEmail(mailMessage).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                log.debug("Send successful");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable){
                log.error("Send failed", throwable);
            }
        });

    }
}
