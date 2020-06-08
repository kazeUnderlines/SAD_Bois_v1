package com.example.ds.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class rdbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "receipt.db";
    private static final int DATABASE_VERSION = 1;
    public rdbHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_ORDER_TABLE = "CREATE TABLE "+
                rdbContract.orderEntry.TABLE_NAME + " (" +
                rdbContract.orderEntry.ORDER_NUMBER + " INTEGER PRIMARY KEY, " +
                rdbContract.orderEntry.DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                rdbContract.orderEntry.PAYMENT + " TEXT, " +
                rdbContract.orderEntry.TOTAL_PRICE + " INTEGER NOT NULL" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_ORDER_TABLE);

        final String SQL_CREATE_ITEM_TABLE = "CREATE TABLE "+
                rdbContract.itemEntry.TABLE_NAME + " (" +
                rdbContract.itemEntry.ORDER_NUMBER + " TEXT, " +
                rdbContract.itemEntry.SERIAL_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                rdbContract.itemEntry.PRODUCT_NAME + " TEXT NOT NULL, " +
                rdbContract.itemEntry.SWEETNESS + " TEXT NOT NULL, " +
                rdbContract.itemEntry.ICE + " TEXT NOT NULL, " +
                rdbContract.itemEntry.AMOUNT + " INTEGER NOT NULL, " +
                rdbContract.itemEntry.SINGLE_PRICE + " INTEGER NOT NULL, "+
                rdbContract.itemEntry.PRICE + " INTEGER NOT NULL, " + "FOREIGN KEY (" +
                rdbContract.itemEntry.ORDER_NUMBER + ") REFERENCES " +
                rdbContract.orderEntry.TABLE_NAME + "(" +
                rdbContract.orderEntry.ORDER_NUMBER +")" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_ITEM_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + rdbContract.orderEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + rdbContract.itemEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



}
