package com.sky.dev.etkan.Core.controllers;

import android.content.Context;


import com.sky.dev.etkan.Core.API.APIModel;
import com.sky.dev.etkan.Core.API.URLBuilder;
import com.sky.dev.etkan.Core.models.PointsModel;
import com.sky.dev.etkan.Core.models.StudentModel;
import com.sky.dev.etkan.Core.parsers.StudentAuthParser;
import com.sky.dev.etkan.Core.parsers.StudentPointsParser;
import com.tradinos.network.Controller;
import com.tradinos.network.FailedCallback;
import com.tradinos.network.RequestMethod;
import com.tradinos.network.SuccessCallback;
import com.tradinos.network.TradinosRequest;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class PromoterController extends ParentController {

    private PromoterController(Context context, FailedCallback failedCallback) {
        super(context, failedCallback);
    }

    public static PromoterController getInstance(Controller controller) {
        return new PromoterController(controller.getmContext(), controller.getmFailedCallback());
    }

//    public void getPromoterNotifications(SuccessCallback<ArrayList<Notification>> successCallback){
//        String url = new URLBuilder(APIModel.promoterNotifications).getURL();
//        TradinosRequest request = new TradinosRequest(getmContext(), url, RequestMethod.Post, new PromoterNotificationsParser(), successCallback, getmFailedCallback());
//        authenticationRequired(request);
//        request.call();
//    }


    public void checkStudent(String userKey, String password, SuccessCallback<StudentModel> successCallback) {
        String url = new URLBuilder(APIModel.checkStudentAuth).getURL();
        TradinosRequest request = new TradinosRequest(getmContext(), url, RequestMethod.Post, new StudentAuthParser(), successCallback, getmFailedCallback());
        request.addParameter("student_id", userKey);
        request.addParameter("password", password);
        request.call();
    }

    public void getStudentPoints(String userKey, SuccessCallback<ArrayList<PointsModel>> successCallback) {
        String url = new URLBuilder(APIModel.getStudentPoints).getURL();
        TradinosRequest request = new TradinosRequest(getmContext(), url, RequestMethod.Post, new StudentPointsParser(), successCallback, getmFailedCallback());
        request.addParameter("student_id", userKey);
        request.call();
    }

}
