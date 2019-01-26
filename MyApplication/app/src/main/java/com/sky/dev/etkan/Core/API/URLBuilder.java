package com.sky.dev.etkan.Core.API;


public class URLBuilder {

    private static final String SERVER_URL = "http://142.93.128.173/schools/Api/";

    private static final boolean RE_WRITING_URL = true;

    private APIModel model;
    private String action;
    private APIFormat format;

    public URLBuilder(APIModel model) {
        this.model = model;
        this.format = APIFormat.JSON;
    }

    public URLBuilder(APIModel model, String action, APIFormat format) {
        this.model = model;
        this.action = action;
        this.format = format;
    }

    public String getURL() {
        String url;
        if (RE_WRITING_URL)
            url = SERVER_URL + "/" + model.toString();
        else
            url = SERVER_URL + "?method=" + model.toString() + "." + action;

        return url;
    }
}