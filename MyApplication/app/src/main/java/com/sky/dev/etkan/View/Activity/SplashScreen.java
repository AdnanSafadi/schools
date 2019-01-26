package com.sky.dev.etkan.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sky.dev.etkan.Core.utils.UserUtils;
import com.sky.dev.etkan.R;
import com.squareup.picasso.Picasso;


public class SplashScreen extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupView();
    }

    private void setupView() {
        imageView = findViewById(R.id.splash_image);
        Picasso.get().load(R.drawable.splash).into(imageView);

        if (UserUtils.isLoggin(this))
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }
}
