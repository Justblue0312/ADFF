package com.example.findjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Student;
import com.mikhaellopez.circularimageview.CircularImageView;


public class MainActivity extends AppCompatActivity {
    private TabHost tabHost;

    private EditText edtSearchJobs;
    private CircularImageView imgAvatar;
    private TextView txtName,txtEmail, txtLocation, txtShortIntro,txtBio, textView;
    private ImageButton btnEditProfile;

    private RecyclerView rvJobs,rvAppliedJobs,rvApprovedJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControls();
        addEvents();

        Bundle bundleRecieve = getIntent().getExtras();
        if (bundleRecieve != null){
            Student student = (Student) bundleRecieve.get("object_student");
            if (student != null){
                textView.setText(student.toString());
            }
        }
    }

    private void addControls(){
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Jobs", getDrawable(R.drawable.ic_baseline_home_24));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Favorite", getDrawable(R.drawable.ic_baseline_fact_check_24));
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Profile", getDrawable(R.drawable.ic_baseline_person_pin_24));
        tabHost.addTab(tab3);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("t1")){
                    Toast.makeText(MainActivity.this, "Jobs", Toast.LENGTH_SHORT).show();
                }else if(tabId.equals("t2")){
                    Toast.makeText(MainActivity.this, "Favorite", Toast.LENGTH_SHORT).show();
                }else if(tabId.equals("t3")){
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edtSearchJobs = findViewById(R.id.edtSearchJobs);
        imgAvatar = findViewById(R.id.imgAvatar);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtLocation = findViewById(R.id.txtLocation);
        txtShortIntro = findViewById(R.id.txtShortIntro);
        txtBio = findViewById(R.id.txtBio);

        textView = findViewById(R.id.textView);

        rvJobs = findViewById(R.id.rvJobs);
        rvAppliedJobs = findViewById(R.id.rvAppliedJobs);
        rvApprovedJobs = findViewById(R.id.rvApprovedJobs);
    }

    private void addEvents() {
    }
}