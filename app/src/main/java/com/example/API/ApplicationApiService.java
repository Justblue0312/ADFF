package com.example.API;

import com.example.Model.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApplicationApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    ApplicationApiService applicationApiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApplicationApiService.class);


    @GET("application/")
    Call<List<Application>> getAppList();

    @GET("application/{id}/")
    Call<Application> getAppInfo(@Path("id") String appId);

    @POST("application/")
    Call<Application> createApp(@Body Application application);

    @PUT("application/{id}/")
    Call<Application> updateAppInfo(@Path("id") String appId, @Body Application application);

    @DELETE("application/{id}/")
    Call<Application> deleteApp(@Path("id") String appId);
}
