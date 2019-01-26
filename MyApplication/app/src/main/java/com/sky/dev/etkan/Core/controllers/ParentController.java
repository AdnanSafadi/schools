package com.sky.dev.etkan.Core.controllers;

import android.content.Context;

import com.tradinos.network.Controller;
import com.tradinos.network.FailedCallback;


class ParentController extends Controller {
    ParentController(Context context, FailedCallback failedCallback) {
        super(context, failedCallback);
    }

//    void authenticationRequired(TradinosRequest request) {
//        try {
//            request.getHeaders().put("auth", UserUtils.getLoggedPromoter(getmContext()).getServerToken());
//        } catch (AuthFailureError authFailureError) {
//            authFailureError.printStackTrace();
//        }
//        /*request.turnOnAuthentication("auth", UserUtils.getLoggedPromoter(getmContext()).getServerToken());*/
//    }
}
