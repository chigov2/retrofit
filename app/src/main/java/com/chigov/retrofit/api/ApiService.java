package com.chigov.retrofit.api;

import com.chigov.retrofit.pojo.EmployeeResponse;

import retrofit2.http.GET;
import io.reactivex.Observable;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}
