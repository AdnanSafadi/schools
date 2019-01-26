package com.sky.dev.etkan.Core.models;

public class PointsModel {
    String studentId;
    String materialId;
    String studenPoint;
    String materialPoint;

    public PointsModel(String studentId, String materialId, String studenPoint, String materialPoint) {
        this.studentId = studentId;
        this.materialId = materialId;
        this.studenPoint = studenPoint;
        this.materialPoint = materialPoint;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getStudenPoint() {
        return studenPoint;
    }

    public void setStudenPoint(String studenPoint) {
        this.studenPoint = studenPoint;
    }

    public String getMaterialPoint() {
        return materialPoint;
    }

    public void setMaterialPoint(String materialPoint) {
        this.materialPoint = materialPoint;
    }
}
