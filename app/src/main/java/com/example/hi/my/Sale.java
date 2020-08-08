package com.example.hi.my;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Sale extends AppCompatActivity {
    private Button closesale,pick,scan ;
    private DBHelper mydb ;
    private String scanContent;
    private Transaction db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);


        pick = (Button)findViewById(R.id.picksell);
        scan = (Button)findViewById(R.id.scansell);
        closesale = (Button)findViewById(R.id.closesell);
        mydb = new DBHelper(this);
        db = new Transaction(this);


    }

    public void picker(View v) {
        Intent intent = new Intent(getApplicationContext(), Products.class);
        startActivity(intent);

    }





    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        Integer monitor =0;
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
            // String scanFormat = scanningResult.getFormatName();

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String dated = df.format(c);
            Cursor rs = mydb.getAll();
            if (rs.moveToFirst()) {
                do {
                    String namer =
                            rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                    String sell =
                            rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

                    String bare =
                            rs.getString(rs.getColumnIndex("barcode"));


                    if (scanContent.equals(bare)) {
                        mydb.insertSale(namer, sell, dated, "sell");
                        db.insertData(namer,sell);
                        monitor = 1;
                    }

                } while (monitor != 1);

                //   mydb.insertSale(namer,sell,dated,"sell");

            }

            if (scanContent == null) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(scanContent != null) {
        IntentIntegrator scanIntegrat = new IntentIntegrator(this);
        scanIntegrat.initiateScan();
            }
    }

    public void scanner(View v) {

        IntentIntegrator scanIntegrat = new IntentIntegrator(this);
        scanIntegrat.initiateScan();

      }

      public void Close(View V){
          Intent intent = new Intent(getApplicationContext(), checkout.class);
          startActivity(intent);

      }

}
