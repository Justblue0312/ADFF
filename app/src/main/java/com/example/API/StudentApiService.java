package com.example.API;

import com.example.Model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StudentApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    StudentApiService studentApiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .build()
            .create(StudentApiService.class);


    @GET("student/")
    Call<Student> getListStudents();
}
