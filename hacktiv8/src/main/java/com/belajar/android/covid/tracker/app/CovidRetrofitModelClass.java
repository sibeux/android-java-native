package com.belajar.android.covid.tracker.app;

import com.google.gson.annotations.SerializedName;

public class CovidRetrofitModelClass {

    @SerializedName("positive")
    private String cases;

    @SerializedName("hospitalized")
    private String recovered;

    @SerializedName("death")
    private String deaths;

    public String getCases() {
        return cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }
}
