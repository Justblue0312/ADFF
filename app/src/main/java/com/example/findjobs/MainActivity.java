package com.example.findjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.API.Const;
import com.example.Model.Student;
import com.example.findjobs.Fragment.JobFragment;
import com.example.findjobs.Fragment.ProfileFragment;
import com.example.findjobs.Fragment.StorageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    private ImageButton btnLogout;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            student = (Student) bundle.get("key_1");
            if (student != null){
                Const.LOGINUSER = student.getUsername();
            }else {
                Toast.makeText(MainActivity.this, "Cannot load data!", Toast.LENGTH_SHORT).show();
            }
        }


        loadFragment(new JobFragment());
        BottomNavigationView bottomNav = findViewById(R.id.navigation_bar);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.navigation_jobs:
                        fragment = new JobFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.navigation_storage:
                        fragment = new StorageFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("LoginUser", student);
                        fragment.setArguments(bundle1);
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void addControls() {
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




}