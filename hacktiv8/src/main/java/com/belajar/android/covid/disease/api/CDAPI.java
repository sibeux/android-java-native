package com.belajar.android.covid.disease.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CDAPI {

    @GET("all")
    Call<CDModelClass> getModelClass();
}
