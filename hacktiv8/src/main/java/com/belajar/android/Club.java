package com.belajar.android;

public class Club {

    private final String teamName;
    private final int logo;

    public Club(String teamName, int logo){
        this.teamName = teamName;
        this.logo = logo;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getLogo() {
        return logo;
    }
}
