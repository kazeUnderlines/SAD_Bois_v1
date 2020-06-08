package com.example.ds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.ds.data.items;

import java.util.ArrayList;

import static com.example.ds.data.SharedData.itemList;

public class SubActivity extends AppCompatActivity {
    int amount, singlePrice;
    String productName, sweetness, ice;
    String ex;
    Spinner spn;
    Button btnC,btnB;
    TextView tvi;
    RadioGroup rgIce, rgSwt;
    RadioButton rbIce,rbSwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        btnC = findViewById(R.id.buttonConfirm);
        tvi = findViewById(R.id.itemTextview);
        spn = findViewById(R.id.amountSpn);
        rgIce = findViewById(R.id.iceRg);
        rgSwt = findViewById(R.id.sweetnessRg);
        btnB = findViewById(R.id.buttonBack);
        ex = this.getIntent().getStringExtra("item");



        tvi.setText(ex);
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                singlePrice = Integer.parseInt(ex.substring(ex.indexOf("$")+1));
                amount = Integer.parseInt(spn.getSelectedItem().toString());
                productName = ex.substring(0,ex.indexOf(" "));
                int rbSwtId = rgSwt.getCheckedRadioButtonId();
                int rbIceId = rgIce.getCheckedRadioButtonId();
                rbSwt = findViewById(rbSwtId);
                rbIce = findViewById(rbIceId);
                sweetness = rbSwt.getText().toString();
                ice = rbIce.getText().toString();
                items item = new items(productName,sweetness,ice,amount,singlePrice);
                itemList.add(item);
                nextAct();
                finish();
            }
        });

        btnB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nextAct();
                finish();
            }
        });

    }

    public void nextAct(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

