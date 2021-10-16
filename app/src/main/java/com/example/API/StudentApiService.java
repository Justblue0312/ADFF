package com.example.API;

import com.example.Model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StudentApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    StudentApiService studentApiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(StudentApiService.class);

    @GET("student/")
    Call<List<Student>> getListStudents();

    @GET("student/{id}/")
    Call<Student> getStudentInfo(@Path("id") String studentId);

    @Multipart
    @POST("student/")
    Call<Student> createAccount(@Part MultipartBody.Part profile_image);

    @PUT("student/{id}/")
    Call<Student> updateStudentInfo(@Path("id") String studentId,
                                    @Body Student student);

}
