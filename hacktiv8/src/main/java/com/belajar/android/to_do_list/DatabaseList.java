package com.belajar.android.to_do_list;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DatabaseList {

    private DatabaseReference databaseReference;

    public DatabaseList(){
        FirebaseDatabase db =  FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ListModuleClass.class.getSimpleName());
    }

    public Task<Void> add(ListModuleClass listModuleClass){
        return databaseReference.push().setValue(listModuleClass);
    }

    public  Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        return databaseReference.orderByKey();
    }
}
