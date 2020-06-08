package com.example.ds;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.example.ds.data.dbAccess;

import java.util.List;
import com.example.ds.data.rdbContract;
import com.example.ds.data.rdbHelper;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private TextView tv;
    static SQLiteDatabase db;
    static rdbHelper rdbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lv);
        tv = findViewById(R.id.tvHot);
        setSupportActionBar(toolbar);
        rdbh = new rdbHelper(this);
        db = rdbh.getWritableDatabase();



        dbAccess da = dbAccess.getInstance(this);
        da.open();
        List<String> products = da.getName();
        da.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,products){
            @Override
            public View getView(int pos, View convert, ViewGroup group) {
                View v = super.getView(pos, convert, group);
                TextView t1 = v.findViewById(android.R.id.text1);
                TextView t2 = v.findViewById(android.R.id.text2);
                t1.setText(getItem(pos).substring(0,getItem(pos).indexOf(" ")));
                t2.setText(getItem(pos).substring(getItem(pos).indexOf(" ")+1));
                return v;
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                String temp = lv.getItemAtPosition(position).toString();
                intent.putExtra("item",temp);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartAct();
            }
        });
        Cursor cursor = getHot();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            String hot = cursor.getString(0);
            tv.setText("今日熱賣:"+hot);
        }else{
            tv.setText("想喝什麼?");
        }
    }

    public Cursor getHot(){
        return db.rawQuery("SELECT "+rdbContract.itemEntry.PRODUCT_NAME+", COUNT(*) FROM "+rdbContract.itemEntry.TABLE_NAME+" GROUP BY "+rdbContract.itemEntry.PRODUCT_NAME+" ORDER BY COUNT(*) DESC;",null);
    }


    public void cartAct(){
        Intent intent = new Intent(this,CartActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
