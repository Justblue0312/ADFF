package com.example.findjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.API.Const;
import com.example.API.StudentApiService;
import com.example.API.UserClient;
import com.example.Model.Job;
import com.example.Model.Login;
import com.example.Model.Student;
import com.example.findjobs.Fragment.JobFragment;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView edtUsername, edtPassword, edtRegister, textView;
    private Button btnLogin;

    private ProgressDialog progressDialog;

    private List<Student> mListStudent;
    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtRegister = findViewById(R.id.edtRegister);
        btnLogin = findViewById(R.id.btnLogin);

        textView = findViewById(R.id.textView);

        mListStudent = new ArrayList<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });

        edtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        
        getListStudent();
    }



    private void loginAccount() {
        progressDialog.show();

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        boolean isHasStudent = false;

        if(mListStudent == null|| mListStudent.isEmpty()){
            return;
        }

        if(username == null || password == null){
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Please enter the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        for(Student student: mListStudent){
            if (username.equals(student.getUsername())){
                isHasStudent = true;
                Const.LOGINUSER = student.getUsername();
                mStudent = student;
                break;
            }
        }

        if(isHasStudent){
            RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"), username);
            RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("multipart/form-data"), password);

            UserClient.userClient.loginAccount(requestBodyUsername, requestBodyPassword)
                    .enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            progressDialog.dismiss();

                            Login userLogin = response.body();
                            if(userLogin != null){

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("key_1", mStudent);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Username or password invalid", Toast.LENGTH_SHORT).show();
        }


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