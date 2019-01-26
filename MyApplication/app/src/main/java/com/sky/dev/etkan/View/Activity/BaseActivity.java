package com.sky.dev.etkan.View.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.sky.dev.etkan.R;
import com.sky.dev.etkan.View.Fragments.PointsFragment;
import com.sky.dev.etkan.View.Fragments.ProfileFragment;
import com.sky.dev.etkan.View.Fragments.StudentActivityPointsFragment;
import com.tradinos.network.Controller;

import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Controller mController;
    int contentViewRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(contentViewRes);
        mContext = this; //assigned in setContentView

        defineController();
        assignUIReferences();
        assignActions();
    }

    @Override
    public void setContentView(int layoutResID) {
        this.contentViewRes = layoutResID;
    }

    protected abstract void getData();

    protected abstract void showData();

    protected abstract void assignUIReferences();

    protected abstract void assignActions();

    public Controller getmController() {
        return mController;
    }

    public void defineController() {

        mController = new Controller(this, (errorCode, message) -> {
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            showSnackBarMessage(message);
            failCallBack();
        });

    }

    protected void failCallBack() {
    }

    protected void showSnackBarMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.dismiss), view -> {
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    protected Toolbar set_toolbar(boolean backArrow, String titleId) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (backArrow) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            Objects.requireNonNull(getSupportActionBar()).setTitle(titleId);
        }
        return toolbar;
    }

    protected void changeFragment(int position) {
        android.support.v4.app.Fragment fragment;
        String tag;
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        int fragmentContainer = R.id.root_view;

        switch (position) {
            case 0:
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(Objects.requireNonNull(fragmentManager.findFragmentByTag("one"))).commit();
                } else {
                    //  if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(fragmentContainer, new ProfileFragment(), "one").commit();

                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("two"))).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("three"))).commit();
                }
                break;

            case 1:
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(Objects.requireNonNull(fragmentManager.findFragmentByTag("two"))).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(fragmentContainer, new PointsFragment(), "two").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("one"))).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("three"))).commit();
                }
                break;
            case 2:
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(Objects.requireNonNull(fragmentManager.findFragmentByTag("three"))).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                       fragmentManager.beginTransaction().add(fragmentContainer, new StudentActivityPointsFragment(), "three").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("one"))).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag("two"))).commit();
                }
                break;


        }
    }


}
