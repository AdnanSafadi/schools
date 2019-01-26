package com.sky.dev.etkan.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.dev.etkan.Core.models.PointsModel;
import com.sky.dev.etkan.Core.models.StudentActivityPoint;
import com.sky.dev.etkan.R;
import com.sky.dev.etkan.View.Adapter.PointsAdapter;
import com.sky.dev.etkan.View.Adapter.StudentActivityAdapter;

import java.util.ArrayList;
import java.util.Random;

public class StudentActivityPointsFragment extends Fragment {
    RecyclerView pointRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_activity_points, container, false);

        setupView(view);
        assignAction();
        return view;
    }

    private void setupView(View view) {
        pointRecyclerView = view.findViewById(R.id.point_recycler_view);
    }

    private void assignAction() {
        ArrayList<StudentActivityPoint> studentActivityPoints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int n = rand.nextInt(50) + 1;
            studentActivityPoints.add(new StudentActivityPoint(String.valueOf(i), String.valueOf(n), "60","10/11/2018"));
        }
        StudentActivityAdapter mAdapter = new StudentActivityAdapter(studentActivityPoints, getActivity());
        pointRecyclerView.setAdapter(mAdapter);
        pointRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
