package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Model.Application;
import com.example.Model.Job;
import com.example.findjobs.Interface.IClickItemAppliedJobListener;
import com.example.findjobs.R;

import java.util.List;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.MyViewHolder> {

    List<Application> appliedJobList;
    Context context;
    IClickItemAppliedJobListener iClickItemAppliedJobListener;

    public AppliedJobAdapter(List<Application> appliedJobList, Context context, IClickItemAppliedJobListener iClickItemAppliedJobListener) {
        this.appliedJobList = appliedJobList;
        this.context = context;
        this.iClickItemAppliedJobListener = iClickItemAppliedJobListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applied_jobs_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Application appliedJob = appliedJobList.get(position);
        if(appliedJob == null){
            return;
        }
        holder.txtTitle.setText(appliedJobList.get(position).getJobpost());
        holder.txtApplicant.setText(appliedJobList.get(position).getApplicant());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemAppliedJobListener.onClickItemAppliedJob(appliedJob);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appliedJobList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView txtApplicant, txtTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            txtApplicant = itemView.findViewById(R.id.txtApplicant);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
