package com.example.API;

import com.example.Model.Job;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface JobApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    JobApiService jobApiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobApiService.class);

    @GET("jobpost/")
    Call<List<Job>> getJobList();

    @GET("jobpost/{id}/")
    Call<Job> getJobInfo(@Part("id") String jobId);
}
