package com.example.hi.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

public class purchases extends AppCompatActivity {
    private DBHelper mydb;
    private Button scan_add,addd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        scan_add = (Button) findViewById(R.id.scan_to_add);
        addd = (Button) findViewById(R.id.add);
        mydb = new DBHelper(this);

    }

    public void AddProduct(View v) {

        Intent intent = new Intent(getApplicationContext(), Adder.class);
        startActivity(intent);
    }
    public void ScanProduct(View v) {

            Intent intent = new Intent(getApplicationContext(), scanAdder.class);
            startActivity(intent);

        }
}


