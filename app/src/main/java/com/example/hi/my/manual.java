package com.example.hi.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class manual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        String sessionId = getIntent().getStringExtra("SESSION");
    }
}