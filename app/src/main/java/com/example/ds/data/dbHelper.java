package com.example.ds.data;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class dbHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "dsdb.db";
    private static final int DATABASE_VERSION = 1;
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}

