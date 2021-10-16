package com.example.findjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.API.UserClient;
import com.example.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "Register";

    private TextView edtUsername, edtPassword, edtEmail, edtFirstName, edtLastName;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName =findViewById(R.id.edtLastName);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                String password = edtPassword.getText().toString().trim();
                String username = edtPassword.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String firstName = edtFirstName.getText().toString().trim();
                String lastName = edtLastName.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "PLease fill the fields", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User(password, username, email, firstName, lastName, true);

                    UserClient.userClient.registerUser(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.isSuccessful()){
                                Log.e(TAG, response.body().toString());
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}