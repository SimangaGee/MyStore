package com.example.hi.my;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class ledger extends AppCompatActivity {
    private DBHelper mydb;
    private Double ttlsale ,ttlpurchase;
    private String pop[],temp[],pep[],seller[][],puch[][],type[],hold[],holder[];
    private Integer  d,i,j,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_ledger);
        mydb = new DBHelper(this);
        mydb.deleteContact("buy");
        // Create an ArrayList of Dessert objects
        pop = new String[1000];
        pep = new String[1000];
        temp = new String[1000];
        seller =new String[1000][1000];
        puch =new String[1000][1000];
        type= new String[1000];
        i =j =0;
        k=-1;
        final ArrayList<item> prcdcts = new ArrayList<item>();

       // ArrayList<item> product = new ArrayList<item>();

        ttlsale =0.0; ttlpurchase =0.0;
        //means this is the view part not the add contact part.
        Cursor rs = mydb.getAll();

        prcdcts.add(new item("Date ","Transaction ","Sales", "Purchases"));
        if(rs.moveToFirst()) {
            do {


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
               // prcdcts.add(new item(typ,namer,sell, buy));
                pop[i] = namer;
               // temp[i] = buy;
                //pep[i] = sell;

               // prcdcts.add(new item(pop[i],temp[i],pep[i], buy));
                 i =i+1;

            }while (rs.moveToNext());
          //  prcdcts.add(new item(" ","  ",ttlsale.toString(), ttlpurchase.toString()));



           String hold;

            holder = new String[1000];
           int ff =0;
            Integer doe  = mydb.numberOfRows();
            //int d= 0;

            for (d =0; d<=doe; d=d+1)
             {
                hold = pop[d] ;
                // holder[d] = pop[d] ;
                 for(Integer dd =0; dd<=doe; dd=dd+1)
                   {
                     if(hold==pop[dd] )
                        {
                          if(d.intValue() ==dd.intValue()) {

                            prcdcts.add(new item(pop[d]," ",dd.toString(),d.toString() ));
                          }
                          else
                          {
                              pop[d] = "b";
                          }
                        }
                   }
                // prcdcts.add(new item(pop[d],temp[d],"  ","  " ));
             }

           /*for (d =0; d<=doe; d=d+1)
             {
                if (pop[d].equals())
                {
                    prcdcts.add(new item(pop[d], "", "", ""));
                }

             }*/
        }

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



               // Toast toast = Toast.makeText(getApplicationContext(),
               //         "    "+price +"  "+name+"", Toast.LENGTH_SHORT);
                //toast.show();

                //mydb.insertSale(name, price,dated,"sell");
                //Dessert dessert = desserts.get(i);

                //     Intent pastry = new Intent(Products.this, Sale.class);
                //      startActivity(pastry);

            }
        });

        Integer d  = mydb.numberOfRows();
        String dd = d.toString();
       // Toast toast = Toast.makeText(getApplicationContext(),
        //        ""+dd+"", Toast.LENGTH_SHORT);
       // toast.show();
    }

}