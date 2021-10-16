package com.example.findjobs.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.API.Const;
import com.example.API.StudentApiService;
import com.example.Model.Student;
import com.example.findjobs.EditProfileActivity;
import com.example.findjobs.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    public static final String TAG = "PROFILE";
    List<Student> mListStudent;
    Student mStudent;

    ImageButton btnEditProfile;
    ImageView imgAvatar;
    TextView txtUsername, txtEmail, txtLocation, txtGithub, txtTwitter, txtLinkedin, txtYoutube, txtWebsite,
            txtShortIntro, txtBio;

    public ProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        mStudent = (Student) getArguments().get("LoginUser");
//        Log.e(TAG, mStudent.toString());

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgAvatar = view.findViewById(R.id.imgAvatar);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtLocation = view.findViewById(R.id.txtLocation);
        txtGithub = view.findViewById(R.id.txtGithub);
        txtTwitter = view.findViewById(R.id.txtTwitter);
        txtLinkedin = view.findViewById(R.id.txtLinkedin);
        txtYoutube = view.findViewById(R.id.txtYoutube);
        txtWebsite = view.findViewById(R.id.txtWebsite);
        txtShortIntro = view.findViewById(R.id.txtShortIntro);
        txtBio = view.findViewById(R.id.txtBio);

        mListStudent = new ArrayList<>();

        getListStudent();

        Glide.with(getActivity()).load(mStudent.getProfile_image()).into(imgAvatar);
        txtUsername.setText(mStudent.getUsername());
        txtEmail.setText(mStudent.getEmail());
        txtLocation.setText(mStudent.getLocation());

        txtGithub.setText(mStudent.getSocial_github());
        txtGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStudent.getSocial_github()));
                startActivity(intent);
            }
        });
        txtLinkedin.setText(mStudent.getSocial_linkedin());
        txtLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStudent.getSocial_linkedin()));
                startActivity(intent);
            }
        });

        txtTwitter.setText(mStudent.getSocial_twitter());
        txtTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStudent.getSocial_twitter()));
                startActivity(intent);
            }
        });

        txtYoutube.setText(mStudent.getSocial_youtube());
        txtYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStudent.getSocial_youtube()));
                startActivity(intent);
            }
        });

        txtWebsite.setText(mStudent.getSocial_website());
        txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStudent.getSocial_website()));
                startActivity(intent);
            }
        });

        txtShortIntro.setText(mStudent.getShort_intro());
        txtBio.setText(mStudent.getBio());


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student_key" , mStudent);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }


    private void getListStudent() {
        StudentApiService.studentApiService.getListStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                mListStudent = response.body();
                Log.e("List Students", mListStudent.size()+"");
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("List Students", "Failed to load");
            }
        });
    }

}
