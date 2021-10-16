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
import com.example.Model.Job;
import com.example.findjobs.Interface.IClickItemJobListener;
import com.example.findjobs.R;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder>{

    List<Job> jobList;
    Context context;
    IClickItemJobListener iClickItemJobListener;

    public JobAdapter(List<Job> jobArrayList, Context context, IClickItemJobListener listener) {
        this.jobList = jobArrayList;
        this.context = context;
        this.iClickItemJobListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Job job = jobList.get(position);
        if(job == null){
            return;
        }
        holder.itemName.setText(jobList.get(position).getTitle());
        //Load image
        String uri = jobList.get(position).getJob_image();
        Glide.with(holder.itemImg.getContext()).load(uri).into(holder.itemImg);

        holder.txtCompanyName.setText(jobList.get(position).getCompany_name());
        holder.txtRequire.setText(String.valueOf(jobList.get(position).getJobs_required()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemJobListener.onClickItemJob(job);
            }
        });
    }



    @Override
    public int getItemCount() {
        return jobList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImg;
        TextView itemName, txtCompanyName, txtRequire;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            itemName = itemView.findViewById(R.id.itemName);
            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            txtRequire = itemView.findViewById(R.id.txtRequire);
            linearLayout = itemView.findViewById(R.id.layout_id);
    }
    }

}


