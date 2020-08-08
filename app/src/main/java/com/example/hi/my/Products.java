package com.example.hi.my;

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

public class Products extends AppCompatActivity {
    private DBHelper mydb;
    private Transaction db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mydb = new DBHelper(this);
        db = new Transaction(this);
        // Create an ArrayList of Dessert objects
        final ArrayList<item> prcdcts = new ArrayList<item>();

        //means this is the view part not the add contact part.
        Cursor rs = mydb.getProduct();


        prcdcts.add(new item("Product", "Price"));
        if(rs.moveToFirst()) {
         do {
             String namer =
                     rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
             String sell =
                     rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

             prcdcts.add(new item(namer, sell));
         }while (rs.moveToNext());

        }



        // Create an {@link DessertAdapter}, whose data source is a list of
        // {@link Dessert}s. The adapter knows how to create list item views for each item
        // in the list.
        itemAdapter flavorAdapter = new itemAdapter(this, prcdcts);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(flavorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                item picked = (item) adapterView.getItemAtPosition(i);
                String name = picked.getProductName();
                String price = picked.getProductPrice();

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String dated = df.format(c);


                if( db.insertData(name,price)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            " " + dated + "   " + price + "  " + name + "", Toast.LENGTH_SHORT);
                    toast.show();
                }
                mydb.insertSale(name, price,dated,"sell");

                //Dessert dessert = desserts.get(i);

                   //     Intent pastry = new Intent(Products.this, Sale.class);
                   //      startActivity(pastry);

            }
        });

        Integer d  = mydb.numberOfRows();
        String dd = d.toString();
        Toast toast = Toast.makeText(getApplicationContext(),
                ""+dd+"", Toast.LENGTH_SHORT);
        toast.show();
    }

}

