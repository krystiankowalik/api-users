package com.krystiankowalik.apiresourceusers.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Component
public class ApiClientConfig {

    @Value("${email.server.address}")
    private String BASE_URL;

    @Bean
    private Retrofit retrofit() {

        val clientBuilder = new OkHttpClient.Builder();

        // Add logging interceptor to see communication between app and server
        val loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add interceptors to OkHttpClient
        clientBuilder.addInterceptor(loggingInterceptor);
        // Set timeouts
        clientBuilder.connectTimeout(1, TimeUnit.MINUTES);
        clientBuilder.writeTimeout(1, TimeUnit.MINUTES);
        clientBuilder.readTimeout(1, TimeUnit.MINUTES);

        // Create retrofit instance

        // Create service(interface) instance
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .client(clientBuilder.build())
                .build();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public ApiEmailService apiEmailService(){
        return retrofit().create(ApiEmailService.class);
    }

}