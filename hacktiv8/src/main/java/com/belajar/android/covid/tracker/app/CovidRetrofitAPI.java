package com.belajar.android.covid.tracker.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidRetrofitAPI {

    @GET("current.json")
    Call<List<CovidRetrofitModelClass>> getModelClass();
}
