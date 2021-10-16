package com.example.findjobs.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.Const;
import com.example.API.JobApiService;
import com.example.Adapter.JobAdapter;
import com.example.Model.Job;
import com.example.findjobs.Interface.IClickItemJobListener;
import com.example.findjobs.JobDetailActivity;
import com.example.findjobs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobFragment extends Fragment{

    public static final String TAG = "JOBS";

    private TextView edtSearchJobs;
    private RecyclerView rvJobs;
    private List<Job> jobList;
    private JobAdapter jobAdapter;

    public JobFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtSearchJobs = view.findViewById(R.id.edtSearchJobs);
        jobList = new ArrayList<>();

        rvJobs = view.findViewById(R.id.rvJobs);
        rvJobs.setHasFixedSize(true);
        rvJobs.setLayoutManager(new LinearLayoutManager(getActivity()));


        JobApiService.jobApiService.getJobList().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                jobList = response.body();
                Log.e(TAG, jobList.size()+"");
                jobAdapter = new JobAdapter(jobList, getContext(), new IClickItemJobListener() {
                    @Override
                    public void onClickItemJob(Job job) {
                        OnClickJobDetailListener(job);
                    }
                });
                rvJobs.setAdapter(jobAdapter);

            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e(TAG, "Call API Error");
            }
        });

        edtSearchJobs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null) {
                    loadData(s.toString());
                } else {
                    loadData("");
                }
            }
        });


    }

    private void loadData(String s) {
        //load Data to search
    }



    private void OnClickJobDetailListener(Job job) {
        Intent intent = new Intent(getContext(), JobDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key_job", job);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }
}
