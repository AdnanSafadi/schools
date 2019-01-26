package com.sky.dev.etkan.View.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.sky.dev.etkan.R;

public class MainActivity extends BaseActivity {


    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                set_toolbar(false, "الملف الشخصي");
                changeFragment(0);
                return true;
            case R.id.navigation_dashboard:
                set_toolbar(false, "العلامات");
                changeFragment(1);
                return true;
//            case R.id.navigation_notifications:
//                set_toolbar(false, "النقاط");
//                changeFragment(2);
//                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void showData() {

    }

    @Override
    protected void assignUIReferences() {
        navigation = findViewById(R.id.navigation);
        set_toolbar(false, "الملف الشخصي");

    }

    @Override
    protected void assignActions() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(0);
    }

}
