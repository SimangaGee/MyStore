package com.example.hi.my;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class flash extends AppCompatActivity {
    private Button logbtn;
    private String code;
    private EditText username;
    private ProgressBar load;
    private TextView bae;
    private lock ldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        bae =(TextView)findViewById(R.id.bae) ;
        username =(EditText)findViewById(R.id.username) ;
        logbtn =(Button) findViewById(R.id.login) ;
        load =(ProgressBar) findViewById(R.id.bar) ;
        ldb = new lock(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // This method will be executed once the timer is over
                int count =ldb.numberOfRows();

                if(count>0) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    bae.setVisibility(INVISIBLE);
                    load.setVisibility(INVISIBLE);
                    username.setVisibility(VISIBLE);
                    logbtn.setVisibility(VISIBLE);

                   // finish();
                }


            }
        }, 500);



    }



    public void logIn(View v)
    {
        String temp = username.getText().toString();
        code ="abcde12345phila";



        if(temp.equals(code)) {
            ldb.insertData(temp);
            Intent intent = new Intent(getApplicationContext(), Dash.class);
            startActivity(intent);
        }
    }
}
