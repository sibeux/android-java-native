package com.belajar.android;

public class Club {

    private String teamName;
    private int logo;

    public Club(String teamName, int logo){
        this.teamName = teamName;
        this.logo = logo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
