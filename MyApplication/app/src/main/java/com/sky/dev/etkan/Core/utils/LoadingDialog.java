package com.sky.dev.etkan.Core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.sky.dev.etkan.R;

import java.util.Objects;


public class LoadingDialog {

    private Dialog mDialog;

    private LoadingDialog(Activity mActivity) {
        mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.loading_dialog_layout);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(false);
    }

    public static LoadingDialog getInstance(Activity mActivity) {
        return new LoadingDialog(mActivity);
    }

    public void showDialog() {
        mDialog.show();
    }

    public void closeDialog() {
        mDialog.dismiss();
    }
}
