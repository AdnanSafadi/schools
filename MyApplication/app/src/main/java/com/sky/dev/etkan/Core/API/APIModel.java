package com.sky.dev.etkan.Core.API;


public enum APIModel {

    getStudentPoints, checkStudentAuth;

    @Override
    public String toString() {

        switch (this) {

            case getStudentPoints:
                return "student_point_list";

            case checkStudentAuth:
                return "student_point_list";

            default:
                return "";
        }
    }
}

