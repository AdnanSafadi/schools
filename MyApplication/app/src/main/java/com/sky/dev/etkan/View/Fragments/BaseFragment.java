package com.sky.dev.etkan.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.dev.etkan.Core.utils.LoadingDialog;

public abstract class BaseFragment extends Fragment {
    private View view;
    private int layoutId;
    private LoadingDialog mLoadingDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(this.layoutId, container, false);
        mLoadingDialog = LoadingDialog.getInstance(getActivity());
        findViewsById(view);
        assignActions();
        getData();
        return view;
    }

    protected abstract void findViewsById(View view);

    protected abstract void assignActions();

    protected abstract void getData();

    protected View findViewById(int layoutId) {
        return view.findViewById(layoutId);
    }

    protected void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    protected void showLoading() {
        mLoadingDialog.showDialog();
    }

    protected void closeLoading() {
        mLoadingDialog.closeDialog();
    }
}
