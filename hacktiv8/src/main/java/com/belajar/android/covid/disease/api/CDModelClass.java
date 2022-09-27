package com.belajar.android.covid.disease.api;

import com.google.gson.annotations.SerializedName;

public class CDModelClass {

    @SerializedName("cases")
    private String cases;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("deaths")
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
