package com.sky.dev.etkan.View.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.dev.etkan.Core.utils.CircleTransform;
import com.sky.dev.etkan.Core.utils.UserUtils;
import com.sky.dev.etkan.R;
import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    ImageView profile;
    TextView name, email, phone;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        assignUIReferences(view);
        assignAction();
        return view;
    }


    private void assignUIReferences(View view) {
        profile = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        phone = view.findViewById(R.id.profile_phone);
    }

    private void assignAction() {
        Picasso.get().load(R.drawable.icon).transform(new CircleTransform()).into(profile);
        Map userDate = UserUtils.getUserInfo();
        name.setText(userDate.get("first_name") + " " + userDate.get("last_name"));
        email.setText(Objects.requireNonNull(userDate.get("email")).toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            phone.setText(Html.fromHtml(Objects.requireNonNull(userDate.get("address")).toString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            phone.setText(Html.fromHtml(Objects.requireNonNull(userDate.get("address")).toString()));
        }
    }
}
