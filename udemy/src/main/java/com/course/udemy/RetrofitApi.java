package com.course.udemy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("posts")
    Call<List<RetrofitModelClass>> getModelClass();
}
