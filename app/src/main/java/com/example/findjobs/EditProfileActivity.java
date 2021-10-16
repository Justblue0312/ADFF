package com.example.findjobs;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.API.Const;
import com.example.API.StudentApiService;
import com.example.Model.Student;
import com.example.findjobs.Utils.FileUtils;
import com.example.findjobs.Utils.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "PROFILE";
    public static final int MY_REQUEST_CODE= 10;
    Student student;

    private ImageButton btnChangeAvt, btnLogout;
    private Button btnSave;
    private ImageView imgAvatar;
    TextView txtUsername, txtEmail, txtLocation, txtGithub, txtTwitter, txtLinkedin, txtYoutube, txtWebsite,
            txtShortIntro, txtBio;

    Uri mUri;

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                Log.e(TAG, "onActivityResult");
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if (data == null){
                        return;
                    }
                    Uri uri = data.getData();
                    mUri = uri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        imgAvatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        student = (Student) bundle.get("student_key");
//        Log.e(TAG, student.toString());

        imgAvatar = findViewById(R.id.imgAvatar);
        btnChangeAvt = findViewById(R.id.btnChangeAvt);
        btnLogout = findViewById(R.id.btnLogout);
        btnSave = findViewById(R.id.btnSave);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtLocation = findViewById(R.id.txtLocation);
        txtGithub = findViewById(R.id.txtGithub);
        txtTwitter = findViewById(R.id.txtTwitter);
        txtLinkedin = findViewById(R.id.txtLinkedin);
        txtYoutube = findViewById(R.id.txtYoutube);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtShortIntro = findViewById(R.id.txtShortIntro);
        txtBio = findViewById(R.id.txtBio);

        Glide.with(EditProfileActivity.this).load(student.getProfile_image()).into(imgAvatar);
        txtUsername.setText(student.getUsername());
        txtEmail.setText(student.getEmail());
        txtLocation.setText(student.getLocation());

        txtGithub.setText(student.getSocial_github());
        txtLinkedin.setText(student.getSocial_linkedin());
        txtTwitter.setText(student.getSocial_twitter());
        txtYoutube.setText(student.getSocial_youtube());
        txtWebsite.setText(student.getSocial_website());
        txtShortIntro.setText(student.getShort_intro());
        txtBio.setText(student.getBio());

        addControls();
    }

    private void addControls() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChangeAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user = student.getUser();

                String id = student.getId();
                Student student = new Student(user,
                        txtEmail.getText().toString().trim(),
                        txtUsername.getText().toString().trim(),
                        txtLocation.getText().toString().trim(),
                        txtShortIntro.getText().toString().trim(),
                        txtBio.getText().toString().trim(),
                        txtGithub.getText().toString().trim(),
                        txtTwitter.getText().toString().trim(),
                        txtLinkedin.getText().toString().trim(),
                        txtYoutube.getText().toString().trim(),
                        txtWebsite.getText().toString().trim());

                StudentApiService.studentApiService.updateStudentInfo(id, student)
                        .enqueue(new Callback<Student>() {
                            @Override
                            public void onResponse(Call<Student> call, Response<Student> response) {
                                if(response.isSuccessful()){
                                    Student responseStudent = response.body();
                                    Toast.makeText(EditProfileActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, responseStudent.toString());
                                    startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                                }
                            }

                            @Override
                            public void onFailure(Call<Student> call, Throwable t) {
                                Toast.makeText(EditProfileActivity.this, "Updated Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }

        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

}