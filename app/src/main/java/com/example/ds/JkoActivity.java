package com.example.ds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JkoActivity extends AppCompatActivity {
    Button btMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jko);
        btMain = findViewById(R.id.btMain);
        btMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(JkoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
