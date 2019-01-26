package com.sky.dev.etkan.Core.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.sky.dev.etkan.Core.models.StudentModel;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class UserUtils {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "MyEtganPrefsFile";
    private static final String TOKEN = "token";

    private static final String USER_KEY = "user_key";
    private static final String IS_LOGIN = "is_login";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String FATHER_NAME = "father_name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";


    private static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public static void setIsLogin(Context context, Boolean isLogin) {
        init(context);
        try {
            if (isLogin) editor.putBoolean(IS_LOGIN, true);
            else editor.putBoolean(IS_LOGIN, false);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLoggin(Context context) {
        init(context);
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    public static void setUserInfo(Context context, StudentModel studentModel) {
        init(context);
        try {
            editor.putBoolean(IS_LOGIN, true);
            editor.putString(USER_KEY, String.valueOf(studentModel.getUserKey()));
            editor.putString(FIRST_NAME, studentModel.getFirstName());
            editor.putString(LAST_NAME, studentModel.getLastName());
            editor.putString(FATHER_NAME, studentModel.getFathertName());
            editor.putString(ADDRESS, studentModel.getAddress());
            editor.putString(EMAIL, studentModel.getEmail());
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map getUserInfo() {
        return sharedPreferences.getAll();
    }

}

//
//    public static void storeCities(@NonNull Context context, ArrayList<City> cities) {
//        SharedPreferences mSharedPreferences = context.getSharedPreferences(CITIES_SHARED_PREFRENCES, 0);
//        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(cities);
//        mEditor.putString(CITIES_TAG, json);
//        mEditor.apply();
//    }
//
//    public static ArrayList<City> getCities(@NonNull Context context) {
//        SharedPreferences mSharedPreferences = context.getSharedPreferences(CITIES_SHARED_PREFRENCES, 0);
//        Gson gson = new Gson();
//        String json = mSharedPreferences.getString(CITIES_TAG, "");
//        Type type = new TypeToken<ArrayList<City>>() {
//        }.getType();
//        return gson.fromJson(json, type);
//    }



