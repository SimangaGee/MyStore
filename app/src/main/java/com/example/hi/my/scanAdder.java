package com.example.hi.my;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class scanAdder extends AppCompatActivity {

    private DBHelper mydb;
    private TextView name;
    private TextView buy_price;
    private TextView selling_price;
    private TextView quantity;
    private TextView contentTxt;
    private String dated;
    private String scanContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_adder);


        name = (TextView) findViewById(R.id.editTextName);
        buy_price = (TextView) findViewById(R.id.editTextbuy);
        selling_price = (TextView) findViewById(R.id.editTextsell);
        quantity = (TextView) findViewById(R.id.editTextquantity);
       // contentTxt =(TextView) findViewById(R.id.code);

        mydb = new DBHelper(this);



    }


    public void run(View view)
    {



        final String namee = name.getText().toString().trim();
        final String buye = buy_price.getText().toString().trim();
        final String selle = selling_price.getText().toString().trim();
        final String quantitye = quantity.getText().toString().trim();

        if (TextUtils.isEmpty(namee)) {
            name.setError("Please enter name");
            name.requestFocus();

            return;
        }

        if (TextUtils.isEmpty(buye)) {
            buy_price.setError("Please enter a buying price ");
            buy_price.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(selle)) {
            selling_price.setError("Enter a selling price");
            selling_price.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(selle)) {
            selling_price.setError("Enter a password");
            selling_price.requestFocus();

            return;
        }

        if (TextUtils.isEmpty(quantitye)) {
            quantity.setError("Enter a quantity");
            quantity.requestFocus();
            return;
        }

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();


        // int sam = mydb.numberOfRows() ;
        // Toast.makeText(getApplicationContext(), "total  "+sam+"",
        //         Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            // formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            dated = df.format(c);

            if (mydb.insertCode(name.getText().toString(), buy_price.getText().toString(), selling_price.getText().toString(), quantity.getText().toString(), dated, scanContent,"buy")) {
                // int sam = mydb.numberOfRows() ;




            } else {
                Toast.makeText(getApplicationContext(), "not done",
                        Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}


