package com.example.hi.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CashBook extends AppCompatActivity {
    private DBHelper mydb;
    private String datapass;
    private Double ttlsale ,ttlpurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_book);
        mydb = new DBHelper(this);
        mydb.deleteContact("buy");
       // datapass = getIntent().getExtras().getString("keyName");

        // Create an ArrayList of Dessert objects
        final ArrayList<item> prcdcts = new ArrayList<item>();

        ttlsale =0.0; ttlpurchase =0.0;
        //means this is the view part not the add contact part.

        prcdcts.add(new item("Date ","Transaction ","Sales", "Purchases"));
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        Toast.makeText(CashBook.this,
                " : "+ sessionId,
                Toast.LENGTH_SHORT).show();
      //  if( !sessionId.equals("") )
          {
            Cursor rs = mydb.getMonth(sessionId);

              if(rs.moveToFirst()) {
                  while (rs.moveToNext())
                  {
                      String namer =
                              rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                      String sell =
                              rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

                      String buy =
                              rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BUY));

                      String typ =
                              rs.getString(rs.getColumnIndex("type"));

                      String date =
                              rs.getString(rs.getColumnIndex("date"));

                      String bare =
                              rs.getString(rs.getColumnIndex("barcode"));


                      String temp ="buy" ;
                      if(typ.trim().length()== temp.trim().length()) {
                          ttlpurchase += Double.parseDouble(buy);
                          prcdcts.add(new item(date,namer, "-",buy));

                      }
                      else{

                          ttlsale += Double.parseDouble(sell);
                          prcdcts.add(new item(date,namer,sell,"-"));

                      }
                  }
                  prcdcts.add(new item(" ","  ",ttlsale.toString(), ttlpurchase.toString()));

              }
         }
        //else
           /* {
                Cursor rs = mydb.getAll();

                if(rs.moveToFirst()) {
                    while (rs.moveToNext())
                    {
                        String namer =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                        String sell =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

                        String buy =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BUY));

                        String typ =
                                rs.getString(rs.getColumnIndex("type"));

                        String date =
                                rs.getString(rs.getColumnIndex("date"));

                        String bare =
                                rs.getString(rs.getColumnIndex("barcode"));


                        String temp ="buy" ;
                        if(typ.trim().length()== temp.trim().length()) {
                            ttlpurchase += Double.parseDouble(buy);
                            prcdcts.add(new item(date,namer, "-",buy));

                        }
                        else{

                            ttlsale += Double.parseDouble(sell);
                            prcdcts.add(new item(date,namer,sell,"-"));

                        }
                    }
                    prcdcts.add(new item(" ","  ",ttlsale.toString(), ttlpurchase.toString()));

                }
           */ // }







        // Create an {@link DessertAdapter}, whose data source is a list of
        // {@link Dessert}s. The adapter knows how to create list item views for each item
        // in the list.
        bookAdapter flavorAdapter = new bookAdapter(this, prcdcts);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(flavorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                item picked = (item) adapterView.getItemAtPosition(i);
                String name = picked.getProductName();
                String price = picked.getProductPrice();



                Toast toast = Toast.makeText(getApplicationContext(),
                        "    "+price +"  "+name+"", Toast.LENGTH_SHORT);
                toast.show();

                //mydb.insertSale(name, price,dated,"sell");
                //Dessert dessert = desserts.get(i);

                //     Intent pastry = new Intent(Products.this, Sale.class);
                //      startActivity(pastry);

            }
        });

        Integer d  = mydb.numberOfRows();
        String dd = d.toString();
        Toast toast = Toast.makeText(getApplicationContext(),
                ""+sessionId+"", Toast.LENGTH_SHORT);
        toast.show();
    }

}