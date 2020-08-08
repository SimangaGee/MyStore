package com.example.hi.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {
    private Button cashbooks, datas,btnSubmit,btnSubmit2;
    private Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        cashbooks = (Button) findViewById(R.id.cashbook);
        datas = (Button) findViewById(R.id.data);
        // Spinner element

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

   /* public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }*/

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), CashBook.class);
                intent.putExtra("EXTRA_SESSION_ID", spinner1.getSelectedItem().toString());
                startActivity(intent);
            }

        });
    }


    public void cashBook(View v)
     {
         Intent intent = new Intent(getBaseContext(), CashBook.class);
         intent.putExtra("EXTRA_SESSION_ID", "");
         startActivity(intent);
     }

    public void Data(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Data.class);
        startActivity(intent);
    }

    public void backer(View v)
    {
        Intent intent = new Intent(getApplicationContext(), backup.class);
        startActivity(intent);
    }
}
