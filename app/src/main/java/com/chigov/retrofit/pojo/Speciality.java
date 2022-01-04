package com.chigov.retrofit.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speciality {
    @SerializedName("speciality_id")
    @Expose
    private int specialityId;
    @SerializedName("name")
    @Expose
    private String name;

    public int getSpecialtyId() {
        return specialityId;
    }

    public void setSpecialtyId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
