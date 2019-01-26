package com.sky.dev.etkan.Core.models;

import java.io.Serializable;

public class StudentActivityPoint implements Serializable {
    String category;
    String point;
    String totlaPoints;
    String Date;

    public StudentActivityPoint(String category, String point, String totlaPoints, String date) {
        this.category = category;
        this.point = point;
        this.totlaPoints = totlaPoints;
        Date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getTotlaPoints() {
        return totlaPoints;
    }

    public void setTotlaPoints(String totlaPoints) {
        this.totlaPoints = totlaPoints;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
