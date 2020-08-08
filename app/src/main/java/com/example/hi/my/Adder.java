package com.example.hi.my;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adder extends Activity {


    // int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    private TextView name;
    private TextView buy_price;
    private TextView selling_price;
    private TextView quantity;
    private String dated;
    //private TextView place;


    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);

        name = (TextView) findViewById(R.id.editTextName);
        buy_price = (TextView) findViewById(R.id.editTextbuy);
        selling_price = (TextView) findViewById(R.id.editTextsell);
        quantity = (TextView) findViewById(R.id.editTextquantity);
        mydb = new DBHelper(this);



    }

    public void run(View view)
      {   boolean monitor = true;
          Date c = Calendar.getInstance().getTime();
          SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
          dated = df.format(c);

          final String namee = name.getText().toString().trim();
          final String buye = buy_price.getText().toString().trim();
          final String selle = selling_price.getText().toString().trim();
          final String quantitye = quantity.getText().toString().trim();

          if (TextUtils.isEmpty(namee)) {
              name.setError("Please enter name");
              name.requestFocus();
              monitor = false;
              return;
          }

          if (TextUtils.isEmpty(buye)) {
              buy_price.setError("Please enter a buying price ");
              buy_price.requestFocus();
              monitor = false;
              return;
          }


          if (TextUtils.isEmpty(selle)) {
              selling_price.setError("Enter a selling price");
              selling_price.requestFocus();
              monitor = false;
              return;
          }

          if (TextUtils.isEmpty(selle)) {
              selling_price.setError("Enter a password");
              selling_price.requestFocus();
              monitor = false;
              return;
          }

          if (TextUtils.isEmpty(quantitye)) {
              quantity.setError("Enter a quantity");
              quantity.requestFocus();
              monitor = false;
              return;
          }


          if (mydb.insertContact(name.getText().toString(), buy_price.getText().toString(), selling_price.getText().toString(), quantity.getText().toString(), dated,"buy")) {
                  Toast.makeText(getApplicationContext(), "Information well entered ",
                          Toast.LENGTH_SHORT).show();
                  // display();

              } else {
                  Toast.makeText(getApplicationContext(), "not done",
                          Toast.LENGTH_SHORT).show();

          }
       }


// displayer
    //  Bundle extras = getIntent().getExtras();
        //  if(extras !=null)
public void display()
    {
           // int Value = extras.getInt("id")
            int Value =1;
            if (Value > 0) {

//means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String namer =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String buy =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BUY));
                String sell =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));
                String quant =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_QUANTITY));

                if (!rs.isClosed())
                {
                    rs.close();
                }

                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence)namer);
                name.setFocusable(false);
                name.setClickable(false);

                buy_price.setText((CharSequence)buy);
                buy_price.setFocusable(false);
                buy_price.setClickable(false);

                selling_price.setText((CharSequence)sell);
                selling_price.setFocusable(false);
                selling_price.setClickable(false);

                quantity.setText((CharSequence)quant);
                quantity.setFocusable(false);
                quantity.setClickable(false);

            }
        }

}






   /*

}*/
/*    @Override

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);
                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);
                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);
                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);
                place.setEnabled(true);
                place.setFocusableInTouchMode(true);
                place.setClickable(true);
                return true;
            case R.id.Delete_Contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new
                                        Intent(getApplicationContext(),com.example.addressbook.MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
// User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }*//*

*/


