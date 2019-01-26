package com.sky.dev.etkan.Core.controllers;

import android.content.Context;

import com.tradinos.network.Controller;
import com.tradinos.network.FailedCallback;

@SuppressWarnings("unchecked")
public class AppController extends ParentController {

    private AppController(Context context, FailedCallback failedCallback) {
        super(context, failedCallback);
    }

    public static AppController getInstance(Controller controller) {
        return new AppController(controller.getmContext(), controller.getmFailedCallback());
    }



}
