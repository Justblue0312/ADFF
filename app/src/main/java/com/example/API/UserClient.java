package com.example.API;

import com.example.Model.Login;
import com.example.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserClient {
    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    UserClient userClient = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserClient.class);

    @Multipart
    @POST("users/token/")
    Call<Login> loginAccount(@Part(Const.USERNAME) RequestBody username,
                             @Part(Const.PASSWORD) RequestBody password);

    @POST("user/")
    Call<User> registerUser(@Body User user);
}
