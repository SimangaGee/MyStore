package com.example.hi.my;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class checkout extends AppCompatActivity {

    TextView tprice;
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;
    EditText crunchifyEditText;

    float mValueOne, mValueTwo;

    boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;
    private Transaction db;
    private Double total =0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        db = new Transaction(this);
        Cursor rs = db.getAll();
        if (rs.moveToFirst()) {
            do {
                String namer =
                        rs.getString(rs.getColumnIndex("name"));
                String sell =
                        rs.getString(rs.getColumnIndex("price"));

                total = total + Double.parseDouble(sell);

            } while (rs.moveToNext());
        }

            button0 = (Button) findViewById(R.id.buttonZero);
            button1 = (Button) findViewById(R.id.button1);
            button2 = (Button) findViewById(R.id.button2);
            button3 = (Button) findViewById(R.id.button3);
            button4 = (Button) findViewById(R.id.button4);
            button5 = (Button) findViewById(R.id.button5);
            button6 = (Button) findViewById(R.id.button6);
            button7 = (Button) findViewById(R.id.button7);
            button8 = (Button) findViewById(R.id.button8);
            button9 = (Button) findViewById(R.id.button9);
            button10 = (Button) findViewById(R.id.buttonPoint);
            buttonAdd = (Button) findViewById(R.id.buttonAdd);
            buttonSub = (Button) findViewById(R.id.buttonSub);

            buttonDivision = (Button) findViewById(R.id.buttondiv);
            buttonC = (Button) findViewById(R.id.buttonC);
            buttonEqual = (Button) findViewById(R.id.buttonEqual);
            crunchifyEditText = (EditText) findViewById(R.id.edt1);
            tprice = (TextView) findViewById(R.id.text1);

            tprice.setText(total.toString());

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "1");
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "2");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "3");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "4");
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "5");
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "6");
                }
            });

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "7");
                }
            });

            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "8");
                }
            });

            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "9");
                }
            });

            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + "0");
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (crunchifyEditText == null) {
                        crunchifyEditText.setText("");
                    } else {
                        mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                        crunchifyAddition = true;
                        crunchifyEditText.setText(null);
                    }
                }
            });

            buttonSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    mSubtract = true;
                    crunchifyEditText.setText(null);
                }
            });


            buttonDivision.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteData();
                    Intent intent = new Intent(getApplicationContext(), Sale.class);
                    startActivity(intent);
                }
            });

            buttonEqual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValueTwo = Float.parseFloat(crunchifyEditText.getText() + "");

                    Double temp = mValueTwo - total;
                    Toast toast = Toast.makeText(getApplicationContext(),
                            total.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    tprice.setText(temp.toString());
                }
            });

            buttonC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText("");
                }
            });

            button10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crunchifyEditText.setText(crunchifyEditText.getText() + ".");
                }
            });
        }
    }
