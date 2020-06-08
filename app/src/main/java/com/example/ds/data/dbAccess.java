package com.example.ds.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static dbAccess instance;

    private dbAccess(Context context){
        this.openHelper = new dbHelper(context);
    }

    public static dbAccess getInstance(Context context){
        if (instance == null){
            instance = new dbAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if (database !=null){
            this.database.close();
        }
    }

    public List<String> getName(){
        ArrayList list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT _product || ' ' || '$' || _price FROM products WHERE _status == 1",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}

