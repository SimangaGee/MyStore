package com.example.hi.my;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Dash extends AppCompatActivity {
    private Button clicker;
    private Account accdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        clicker =findViewById(R.id.clicker);

            accdb = new Account(this);

            // Create an ArrayList of Dessert objects
            final ArrayList<item> prcdcts = new ArrayList<item>();

            //means this is the view part not the add contact part.
            Cursor rs = accdb.getData();


          //  prcdcts.add(new item("Product", "Price"));
            if(rs.moveToFirst()) {
                do {
                    String namer =
                            rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));


                    prcdcts.add(new item(namer,"..."));
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

                    Intent intent = new Intent(getBaseContext(), manual.class);
                    intent.putExtra("SESSION", name);
                    startActivity(intent);

                }
            });


        }

    public void create(View v){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}
