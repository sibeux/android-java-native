package com.belajar.android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperSQLite extends SQLiteOpenHelper {

    // database version
    private static final int DB_VERSION = 1;

    // database name
    private static final String DB_NAME = "WORLD";

    // Country Table Name
    private static final String TABLE_COUNTRY = "country";

    private static final String KEY_ID = "id";
    private static final String COUNTRY_NAME = "country_name";
    private static final String POPULATION = "population";

    public DatabaseHelperSQLite(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCountry = " CREATE TABLE "+TABLE_COUNTRY+" ("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COUNTRY_NAME + " TEXT, "+
                POPULATION + " LONG )";
        db.execSQL(tableCountry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_COUNTRY);
        onCreate(db);
    }

    public List<CountrySQLite> getAllCountries(){
        List<CountrySQLite> countrySQLiteList = new ArrayList<>();

        String allCountry = "SELECT * FROM "+TABLE_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(allCountry, null);

        if(cursor.moveToFirst()){
            do {
                CountrySQLite countrySQLite = new CountrySQLite();
                countrySQLite.setId(Integer.parseInt(cursor.getString(0)));
                countrySQLite.setCountryName(cursor.getString(1));
                countrySQLite.setPopulations(cursor.getLong(2));

                countrySQLiteList.add(countrySQLite);
            }while (cursor.moveToNext());
        }

        return countrySQLiteList;
    }

    void addCountry(CountrySQLite countrySQLite){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME,countrySQLite.getCountryName());
        values.put(POPULATION,countrySQLite.getPopulations());

        Log.i("DBHELPER", countrySQLite.getCountryName());

        db.insert(TABLE_COUNTRY,null,values);
    }

    void editCountry(CountrySQLite countrySQLite,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME,countrySQLite.getCountryName());
        values.put(POPULATION,countrySQLite.getPopulations());

        db.execSQL("UPDATE "+TABLE_COUNTRY+" SET " +COUNTRY_NAME + "='"+countrySQLite.getCountryName()+"', "+
                POPULATION+ "='"+countrySQLite.getPopulations()+
                "' WHERE "+KEY_ID +"="+id);
    }

    void removeCountry(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_COUNTRY+" WHERE "+KEY_ID +"="+id);
    }
}
