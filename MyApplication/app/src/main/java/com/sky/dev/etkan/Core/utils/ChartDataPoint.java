package com.sky.dev.etkan.Core.utils;

public class ChartDataPoint {
    private String Description;
    private float value;

    public ChartDataPoint(String description, float value) {
        Description = description;
        this.value = value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
