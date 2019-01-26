package com.sky.dev.etkan.Core.models;

import java.io.Serializable;

public class StudentModel implements Serializable {
    private int userKey;
    private String firstName;
    private String lastName;
    private String fathertName;
    private String address;
    private String email;

    public StudentModel(int userKey, String firstName, String lastName, String fathertName, String address, String email) {
        this.userKey = userKey;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathertName = fathertName;
        this.address = address;
        this.email = email;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFathertName() {
        return fathertName;
    }

    public void setFathertName(String fathertName) {
        this.fathertName = fathertName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
