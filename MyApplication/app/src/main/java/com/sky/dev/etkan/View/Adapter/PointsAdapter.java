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
import com.sky.dev.etkan.R;

import java.util.ArrayList;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private ArrayList<PointsModel> services;
    private Activity mActivity;

    public PointsAdapter(ArrayList<PointsModel> services, Activity mActivity) {
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
        PointsModel currentItem = services.get(i);

        myViewHolder.category.setText(currentItem.getMaterialId());
        myViewHolder.points.setText(currentItem.getStudenPoint());
        myViewHolder.totalPoints.setText(currentItem.getMaterialPoint());
        myViewHolder.date.setVisibility(View.GONE);
        if (i % 2 == 0) {
            myViewHolder.mRootView.setBackgroundColor(mActivity.getResources().getColor(R.color.dark_grey));
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
            this.category = itemView.findViewById(R.id.category);
            this.points = itemView.findViewById(R.id.points);
            this.totalPoints = itemView.findViewById(R.id.total_point);
            this.date = itemView.findViewById(R.id.date);
        }
    }

}
