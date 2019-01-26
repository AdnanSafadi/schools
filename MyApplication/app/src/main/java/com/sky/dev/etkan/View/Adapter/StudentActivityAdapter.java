package com.sky.dev.etkan.View.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.dev.etkan.Core.models.PointsModel;
import com.sky.dev.etkan.Core.models.StudentActivityPoint;
import com.sky.dev.etkan.R;

import java.util.ArrayList;

public class StudentActivityAdapter  extends RecyclerView.Adapter<StudentActivityAdapter.MyViewHolder> {

    private ArrayList<StudentActivityPoint> services;
    private Activity mActivity;

    public StudentActivityAdapter(ArrayList<StudentActivityPoint> services, Activity mActivity) {
        this.services = services;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.point_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        StudentActivityPoint currentItem = services.get(i);

        myViewHolder.category.setText(currentItem.getCategory());
        myViewHolder.points.setText(currentItem.getPoint());
        myViewHolder.totalPoints.setText(currentItem.getTotlaPoints());
        myViewHolder.date.setText(currentItem.getDate());
        if (i % 2 == 0) {
            myViewHolder.mRootView.setBackgroundColor(mActivity.getResources().getColor(R.color.light_grey));
        } else {
            myViewHolder.mRootView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mRootView;
        private TextView category, points,totalPoints,date;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mRootView = itemView.findViewById(R.id.rootView);
            this.mRootView = itemView.findViewById(R.id.rootView);
            this.category = itemView.findViewById(R.id.category);
            this.points = itemView.findViewById(R.id.points);
            this.totalPoints = itemView.findViewById(R.id.total_point);
            this.date = itemView.findViewById(R.id.date);
        }
    }

}