package com.chigov.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee {
    @SerializedName("f_name")
    @Expose
    private String fName;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("avatr_url")
    @Expose
    private String avatrUrl;
    @SerializedName("speciality")
    @Expose
    private List<Speciality> speciality = null;

    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;}
    public String getlName() {
        return lName;}
    public void setlName(String lName) {
        this.lName = lName;}
    public String getBirthday() {
        return birthday;}
    public void setBirthday(String birthday) {
        this.birthday = birthday;}
    public String getAvatrUrl() {
        return avatrUrl;
    }
    public void setAvatrUrl(String avatrUrl) {
        this.avatrUrl = avatrUrl;
    }
    public List<Speciality> getSpeciality() {
        return speciality;
    }
    public void setSpecialty(List<Speciality> speciality) {
        this.speciality = speciality;
    }
}