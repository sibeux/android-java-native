package com.course.udemy;

import com.google.gson.annotations.SerializedName;

public class RetrofitModelClass {

    private int userId;

    private int id;

    private String title;

//    anotasi ini digunakan jika nama variable dan nama "key"
//    di data json tidak sama (di sini pake subTitle, sedangkan di json pake body)
//    kalo namanya sama, ga perlu dikasih anotasi, kaya 3 variable atas
    @SerializedName("body")
    private String subTitle;

    public int getUserID() {
        return userId;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
