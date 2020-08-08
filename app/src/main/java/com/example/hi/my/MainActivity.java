package com.example.hi.my;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button purchase,sell,back;
    private TextView formatTxt, contentTxt;

    private DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sell = (Button)findViewById(R.id.seller);
        purchase = (Button)findViewById(R.id.purchaser);


        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape

        } else {
            // In portrait
        }
        //scanBtn = (Button)findViewById(R.id.scan_button);
        //formatTxt = (TextView)findViewById(R.id.scan_format);
        //contentTxt = (TextView)findViewById(R.id.scan_content);

        //Date
       // Date c = Calendar.getInstance().getTime();

       // SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
       // String dated = df.format(c);

        // Toast.makeText(getApplicationContext(), dated+"   "+c,
          //      Toast.LENGTH_SHORT).show();


        mydb = new DBHelper(this);

    }

    public void purch(View v){
      //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
           // scanIntegrator.initiateScan();
         // mydb.insertContact("smm","wewwew","sasd asa","sssd sdd");
          Intent intent = new Intent(getApplicationContext(), purchases.class);
          startActivity(intent);
    }

    public void backup(View v){
        //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        // scanIntegrator.initiateScan();
        // mydb.insertContact("smm","wewwew","sasd asa","sssd sdd");
        Intent intent = new Intent(getApplicationContext(), backup.class);
        startActivity(intent);
    }
    public void bookdata(View v){
        //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        // scanIntegrator.initiateScan();
        // mydb.insertContact("smm","wewwew","sasd asa","sssd sdd");
        Intent intent = new Intent(getApplicationContext(), Books.class);
        startActivity(intent);
    }

    public void seller(View v){
        //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        // scanIntegrator.initiateScan();
        // mydb.insertContact("smm","wewwew","sasd asa","sssd sdd");
        Intent intent = new Intent(getApplicationContext(), Sale.class);
        startActivity(intent);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
