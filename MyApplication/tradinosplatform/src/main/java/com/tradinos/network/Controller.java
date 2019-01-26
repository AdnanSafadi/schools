package com.tradinos.network;

import android.content.Context;

/**
 * Created by malek on 4/15/16.
 */
public  class Controller {
    private Context mContext ;
    private FailedCallback mFailedCallback;


    public Controller (Context context , FailedCallback failedCallback){
        this.mContext = context ;
        this.mFailedCallback = failedCallback;

    }

    public Context getmContext() {
        return mContext;
    }

    public FailedCallback getmFailedCallback() {
        return mFailedCallback;
    }


}
