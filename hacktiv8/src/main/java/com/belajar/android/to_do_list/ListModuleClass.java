package com.belajar.android.to_do_list;

import com.google.firebase.database.Exclude;

public class ListModuleClass {

    @Exclude
    private String key;
    private String list;
    private String check;

    public ListModuleClass(String list, String check){
        this.list = list;
        this.check = check;
    }

    public ListModuleClass() {};

    public String getList() {
        return list;
    }

    public String getCheck() {
        return check;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
