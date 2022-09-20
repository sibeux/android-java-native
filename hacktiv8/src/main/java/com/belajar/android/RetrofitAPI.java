package com.belajar.android;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("users")
    Call<List<RetrofitModelClass>> getModelClass();
}
