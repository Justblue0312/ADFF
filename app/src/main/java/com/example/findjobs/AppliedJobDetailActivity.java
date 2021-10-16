package com.example.findjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Application;

public class AppliedJobDetailActivity extends AppCompatActivity {

    private TextView txtTitle, txtCheck, txtApproved, txtApplicant;
    private EditText edtDesc;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_detail_job);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Application application = (Application) bundle.get("app_key");

        txtTitle = findViewById(R.id.txtTitle);
        txtApplicant = findViewById(R.id.txtApplicant);
        txtCheck = findViewById(R.id.txtCheck);
        txtApproved = findViewById(R.id.txtApproved);
        edtDesc = findViewById(R.id.edtDesc);

        btnBack = findViewById(R.id.btnBack);

        txtTitle.setText(application.getJobpost());
        txtApplicant.setText(application.getApplicant());
        txtCheck.setText(application.getIs_checked());
        if(!application.isIs_approved()){
            txtApproved.setText("Đã được xét duyệt");
            Toast.makeText(AppliedJobDetailActivity.this, "Liên lạc với nhà tuyển dụng để lấy thêm thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            txtApproved.setText("Chưa được xét duyệt");
        }
        edtDesc.setText(application.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppliedJobDetailActivity.this, MainActivity.class));
            }
        });
    }
}
