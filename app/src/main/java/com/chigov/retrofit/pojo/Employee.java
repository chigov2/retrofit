package com.chigov.retrofit.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "employees")
//@TypeConverters(value = Converter.class)
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("f_name")
    @Expose
    private String name;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("avatr_url")
    @Expose
    private String avatrUrl;
//    @SerializedName("speciality")
//    @Expose
//    private List<Speciality> speciality = null;
    public int getId() {
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fName) {
        this.name = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
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
//    public List<Speciality> getSpeciality() {
//        return speciality;
//    }
//    public void setSpecialty(List<Speciality> speciality) {
//        this.speciality = speciality;
//    }
}
