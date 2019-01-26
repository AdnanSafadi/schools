package com.sky.dev.etkan.View.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sky.dev.etkan.Core.controllers.PromoterController;
import com.sky.dev.etkan.Core.utils.UserUtils;
import com.sky.dev.etkan.R;
import com.tradinos.network.Controller;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void assignActions() {
        mEmailSignInButton.setOnClickListener(view -> attemptLogin());
    }

    @Override
    protected void assignUIReferences() {
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        set_toolbar(false, "تسجيل الدخول");
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void showData() {

    }

//    @Override
//    protected void getData() {
//
//    }
//
//    @Override
//    protected void showData() {
//
//    }
//
//    @Override
//    protected void assignUIReferences() {
//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
//        mEmailView = findViewById(R.id.email);
//        mPasswordView = findViewById(R.id.password);
//        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
//    }
//
//    @Override
//    protected void assignActions() {
//
//        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
//            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
//                attemptLogin();
//                return true;
//            }
//            return false;
//        });
//
//
//        mEmailSignInButton.setOnClickListener(view -> attemptLogin());
//    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            showProgress(true);

            PromoterController.getInstance(new Controller(this, (errorCode, message) -> {
                showProgress(false);
                Toast.makeText(this, "خطآ في كلمة الرقم آو كلمة السر", Toast.LENGTH_LONG).show();
            })).checkStudent(email, password, result -> {
                showProgress(false);
                UserUtils.setUserInfo(this,result);
                startActivity(new Intent(this, MainActivity.class));
            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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

