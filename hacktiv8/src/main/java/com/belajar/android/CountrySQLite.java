package com.belajar.android;

public class CountrySQLite {
    int id;
    String countryName;
    long populations;

    public CountrySQLite(int id, String countryName, long populations) {
        this.id = id;
        this.countryName = countryName;
        this.populations = populations;
    }

    public CountrySQLite() {

    }

    public CountrySQLite(String countryName, long populations) {
        this.countryName = countryName;
        this.populations = populations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getPopulations() {
        return populations;
    }

    public void setPopulations(long populations) {
        this.populations = populations;
    }
}
