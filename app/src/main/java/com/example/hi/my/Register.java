package com.example.hi.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Spinner spin;
    private String business,password,name;
    private EditText passwordt, namet;
    private Button create;
    private Account accdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accdb = new Account(this);
        spin = (Spinner) findViewById(R.id.spinners);
        namet = findViewById(R.id.editTextName);
        passwordt = findViewById(R.id.editTextPassword);


    }
        public void addListenerOnSpinnerItemSelection() {
            spin.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        }

        public void reg(View v){

            addListenerOnSpinnerItemSelection();

            business = spin.getSelectedItem().toString();
            password = passwordt.getText().toString();
            name = namet.getText().toString();

            if(accdb.insertData(name,password,business))
            {
                Toast.makeText(Register.this,
                        business+" : "+password+"  "+name,
                        Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(getApplicationContext(), Dash.class);
              //  startActivity(intent);
            }
        }



        // get the selected dropdown list value


}
