package com.sky.dev.etkan.View.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sky.dev.etkan.Core.controllers.PromoterController;
import com.sky.dev.etkan.Core.models.PointsModel;
import com.sky.dev.etkan.Core.utils.UserUtils;
import com.sky.dev.etkan.R;
import com.sky.dev.etkan.View.Adapter.PointsAdapter;
import com.tradinos.network.Controller;

import java.util.Objects;

public class PointsFragment extends Fragment {

    RecyclerView pointRecyclerView;
    ProgressBar mProgressView;
    TextView emptyData;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_points, container, false);
        setupView(view);
        assignAction();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            assignAction();
        });
        return view;
    }

    private void setupView(View view) {
        pointRecyclerView = view.findViewById(R.id.point_recycler_view);
        mProgressView = view.findViewById(R.id.login_progress);
        emptyData = view.findViewById(R.id.empty_date);
        mSwipeRefreshLayout = view.findViewById(R.id.mSwipeRefreshLayout);
    }

    private void assignAction() {
        showProgress(true);
        PromoterController.getInstance(new Controller(getContext(), (errorCode, message) -> {
            showProgress(false);
        })).getStudentPoints("1", result -> {
            showProgress(false);
//            ArrayList<PointsModel> pointsModels = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                pointsModels.add(new PointsModel(String.valueOf(i), "57", "60", "10/11/2018"));
//            }

            if (result.isEmpty()) {
                emptyData.setVisibility(View.VISIBLE);
                pointRecyclerView.setVisibility(View.GONE);
            } else {
                emptyData.setVisibility(View.GONE);
                PointsAdapter mAdapter = new PointsAdapter(result, getActivity());
                pointRecyclerView.setAdapter(mAdapter);
                pointRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        pointRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        pointRecyclerView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                pointRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

}
