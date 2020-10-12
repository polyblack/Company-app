package com.polyblack.company.api;

import com.polyblack.company.pojo.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface  ApiService {
    @GET("testTask.json")
    Observable<Response> getEmployees();
}
