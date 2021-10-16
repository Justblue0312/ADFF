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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApplicationApiService;
import com.example.Adapter.AppliedJobAdapter;
import com.example.Model.Application;
import com.example.findjobs.AppliedJobDetailActivity;
import com.example.findjobs.Interface.IClickItemAppliedJobListener;
import com.example.findjobs.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StorageFragment extends Fragment {

    public static final String TAG = "APPLIED_JOB";
    TextView edtSearchJobs;
    RecyclerView rvAppliedJobs;
    List<Application> appliedJobList;
    private AppliedJobAdapter appliedJobAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_storage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtSearchJobs = view.findViewById(R.id.edtSearchJobs);
        appliedJobList = new ArrayList<>();

        rvAppliedJobs = view.findViewById(R.id.rvAppliedJobs);
        rvAppliedJobs.setHasFixedSize(true);
        rvAppliedJobs.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApplicationApiService.applicationApiService.getAppList()
                .enqueue(new Callback<List<Application>>() {
                    @Override
                    public void onResponse(Call<List<Application>> call, Response<List<Application>> response) {
                        appliedJobList = response.body();
                        Log.e(TAG, appliedJobList.size()+"");
                        appliedJobAdapter = new AppliedJobAdapter(appliedJobList, getContext(), new IClickItemAppliedJobListener() {
                            @Override
                            public void onClickItemAppliedJob(Application application) {
                                OnClickAppliedJobDetailListener(application);
                            }
                        });
                        rvAppliedJobs.setAdapter(appliedJobAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Application>> call, Throwable t) {
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

    private void loadData(String toString) {
        //load Data to search
    }

    private void OnClickAppliedJobDetailListener(Application application) {
        Intent intent = new Intent(getContext(), AppliedJobDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("app_key", application);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }
}


