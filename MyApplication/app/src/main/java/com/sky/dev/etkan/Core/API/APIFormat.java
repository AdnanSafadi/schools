package com.sky.dev.etkan.Core.API;

public enum APIFormat {

    JSON ,
    XML ,
    HTML ,
    RSS;
    @Override
    public String toString (){
        switch (this) {

            case JSON :
                return "json";
            case XML :
                return "xml";
            case HTML :
                return "html";
            case RSS :
                return "rss";
            default :
                return "";
        }
    }

}