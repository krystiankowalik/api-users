package com.krystiankowalik.apiresourceusers.network;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiEmailService {

   /* @GET("/users")
    public Call<List<UserEntity>> getUsers(
            @Query("per_page") int per_page,
            @Query("page") int page);

    @GET("/users/{username}")
    public Call<UserEntity> getUserEntity(@Path("username") String username);*/

    @GET("/get")
    Call<String> get();

    @POST("/email")
    Call<Void> sendEmail(@Body EmailMessage emailMessage);
}