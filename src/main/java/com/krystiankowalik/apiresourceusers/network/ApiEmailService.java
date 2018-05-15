package com.krystiankowalik.apiresourceusers.network;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiEmailService {

   /* @GET("/users")
    public Call<List<User>> getUsers(
            @Query("per_page") int per_page,
            @Query("page") int page);

    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);*/

    @GET("/get")
    Call<String> get();

    @POST("/email")
    Call<Void> sendEmail(@Body EmailMessage emailMessage);
}