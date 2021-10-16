package com.example.findjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.API.ApplicationApiService;
import com.example.API.Const;
import com.example.Model.Application;
import com.example.Model.Job;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailActivity extends AppCompatActivity {

    public static final String TAG= "JOBPOST";

    private ImageButton btnLogout;
    private ImageView img_JobImage;
    private TextView txtTitle, txtCompanyName, txtCompanyLocation, txtCompanyPhone, txtCompanyEmail, txtJobsDesc, txtRequire, txtAuthor;
    Button btnApply, btnBack;

    Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_JobImage = findViewById(R.id.img_JobImage);
        txtTitle = findViewById(R.id.txtTitle);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCompanyLocation = findViewById(R.id.txtCompanyLocation);
        txtCompanyPhone = findViewById(R.id.txtCompanyPhone);
        txtCompanyEmail = findViewById(R.id.txtCompanyEmail);
        txtJobsDesc = findViewById(R.id.txtJobsDesc);
        txtRequire = findViewById(R.id.txtRequire);
        txtAuthor = findViewById(R.id.txtAuthor);

        btnBack = findViewById(R.id.btnBack);
        btnApply = findViewById(R.id.btnApply);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        job = (Job) bundle.get("key_job");

        Glide.with(JobDetailActivity.this).load(job.getJob_image()).into(img_JobImage);
        txtTitle.setText(job.getTitle());
        txtCompanyName.setText(job.getCompany_name());
        txtCompanyLocation.setText(job.getCompany_location());
        txtCompanyEmail.setText(job.getCompany_email());
        txtJobsDesc.setText(job.getJobs_desc());
        txtRequire.setText(String.valueOf(job.getJobs_required())+ " người");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobDetailActivity.this, MainActivity.class));
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mProgressDialog = new ProgressDialog(JobDetailActivity.this);
                mProgressDialog.setMessage("Please wait...");

                Application application = new Application(job.getTitle(), Const.LOGINUSER, "Helen", false, "");

                ApplicationApiService.applicationApiService.createApp(application)
                        .enqueue(new Callback<Application>() {
                            @Override
                            public void onResponse(Call<Application> call, Response<Application> response) {
                                mProgressDialog.dismiss();
                                if(response.isSuccessful()){
                                    Log.e(TAG, response.body().toString());
                                    Toast.makeText(JobDetailActivity.this, "Apply Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Application> call, Throwable t) {
                                mProgressDialog.dismiss();
                                Log.e(TAG, "Unable to submit post to API.");
                                Toast.makeText(JobDetailActivity.this, "Apply Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }




}