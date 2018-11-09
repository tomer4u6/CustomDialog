package com.example.tomer.customdialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button retBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        retBtn = (Button)findViewById(R.id.retBtn);
    }

    public void backClick(View view) {
        Intent t = new Intent(this, MainActivity.class);
        startActivity(t);
    }
}