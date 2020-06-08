package com.example.ds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.ds.data.items;
import com.example.ds.data.rdbContract;
import com.example.ds.data.rdbHelper;

import java.util.ArrayList;

import static com.example.ds.data.SharedData.itemList;
import static com.example.ds.data.SharedData.orderSerial;

public class CartActivity extends AppCompatActivity {
    ListView lv;
    Button btCash, btJKO, btBack;
    rdbHelper rdbh;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rdbh = new rdbHelper(this);
        db = rdbh.getWritableDatabase();
        btCash = findViewById(R.id.btCash);
        btJKO = findViewById(R.id.btJKO);
        btBack = findViewById(R.id.btBack);
        lv = findViewById(R.id.cartLv);
        ArrayAdapter adapter = new ArrayAdapter<items>(this, android.R.layout.simple_list_item_2,android.R.id.text1,itemList) {
            @Override
            public View getView(int pos, View convert, ViewGroup group) {
                View v = super.getView(pos, convert, group);
                TextView t1 = v.findViewById(android.R.id.text1);
                TextView t2 = v.findViewById(android.R.id.text2);
                t1.setText(getItem(pos).getAmount() + "x " + getItem(pos).getProductName());
                t2.setText(getItem(pos).getSweetness() + " " + getItem(pos).getIce() + " "+ "$" + getItem(pos).getPrice() );
                return v;
            }
        };
        lv.setAdapter(adapter);

        btCash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insertToDb("Cash");
                Toast toast=Toast.makeText(getApplicationContext(),"請持單據至櫃台結帳!",Toast.LENGTH_SHORT);
                toast.show();
                mainAct();
                finish();
            }
        });
        btJKO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insertToDb("JKO");
                jkoAct();
                finish();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mainAct();
                finish();
            }
        });
    }

    public void insertToDb(String payment){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        orderSerial++;
        String orderNumber = date + String.format("%04d",orderSerial);
        int totalPrice = 0;
        for(items p:itemList){
            totalPrice = totalPrice + p.getPrice();
        }
        ContentValues orderCv = new ContentValues();
        orderCv.put(rdbContract.orderEntry.ORDER_NUMBER,orderNumber);
        orderCv.put(rdbContract.orderEntry.DATE,date);
        orderCv.put(rdbContract.orderEntry.PAYMENT,payment);
        orderCv.put(rdbContract.orderEntry.TOTAL_PRICE,totalPrice);
        db.insert(rdbContract.orderEntry.TABLE_NAME,null,orderCv);

        for(items item:itemList) {
            ContentValues itemCv = new ContentValues();
            itemCv.put(rdbContract.itemEntry.ORDER_NUMBER,orderNumber);
            itemCv.put(rdbContract.itemEntry.PRODUCT_NAME,item.getProductName());
            itemCv.put(rdbContract.itemEntry.SWEETNESS,item.getSweetness());
            itemCv.put(rdbContract.itemEntry.ICE,item.getIce());
            itemCv.put(rdbContract.itemEntry.AMOUNT,item.getAmount());
            itemCv.put(rdbContract.itemEntry.SINGLE_PRICE,item.getSinglePrice());
            itemCv.put(rdbContract.itemEntry.PRICE,item.getPrice());
            db.insert(rdbContract.itemEntry.TABLE_NAME,null,itemCv);
        }
        itemList.clear();
    }
    public void mainAct(){
        Intent intent = new Intent(CartActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void jkoAct(){
        Intent intent = new Intent(CartActivity.this,JkoActivity.class);
        startActivity(intent);
    }
}
