package com.example.pbp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ruangfsm.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table ruangan(namaRuangan text primary key, kapasitas integer null);";
        Log.d("Data", "onCreate: " + query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
