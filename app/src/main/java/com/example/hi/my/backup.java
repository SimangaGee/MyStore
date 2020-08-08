package com.example.hi.my;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class backup extends AppCompatActivity {
    Button btnCreate;
    EditText editText;
    private DBHelper mydb;
    private String main,temp1,temp2;
    private int CAMREA_CODE = 1;
    private Integer b;
    private Integer index =0;
    private Integer role;
    private   PdfDocument document ;
    private  PdfDocument.PageInfo pageInfo;
    private Canvas canvas ;
    private Paint paint ;
    // start a page
    private PdfDocument.Page page ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        btnCreate = (Button)findViewById(R.id.buttonSave);
        editText =(EditText) findViewById(R.id.editTextName);
        mydb = new DBHelper(this);

       main ="...";
        requestPermission();



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int doe =0;
               // int i =18;
                String con = "...."+"             "+"....."+"           "+"....."+"            - ";
               // ArrayList<String> type = new ArrayList<String>();
               // ArrayList<String> temp1 = new ArrayList<String>();
               // ArrayList<String> temp2 = new ArrayList<String>();
                document = new PdfDocument();
                pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 0).create();
                // start a page
                page = document.startPage(pageInfo);
                canvas = page.getCanvas();
                paint = new Paint();

               // Canvas canvas = page.getCanvas();
               // Paint paint = new Paint();
                //  paint.setColor(Color.RED);
                //canvas.drawCircle(50, 50, 30, paint);
               // paint.setColor(Color.BLACK);
              Cursor rs = mydb.getAll();
                int i =0;
                int x = 10;
                int y =30;
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

                        String bare =
                                rs.getString(rs.getColumnIndex("barcode"));

                       if(i<19)
                        {
                            if(i ==18)
                              {
                                  document.finishPage(page);
                                  x = 10;
                                   y =30;
                                i = 0;
                              //  int joe =1;
                                doe = doe+1;
                                // create new page
                                  pageInfo = new PdfDocument.PageInfo.Builder(300, 600, doe).create();
                                  // start a page
                                  page = document.startPage(pageInfo);
                                  canvas = page.getCanvas();
                                  paint = new Paint();

                              }
                              else{
                                i = i + 1;
                             }

                           String temp ="buy" ;
                            if(typ.trim().length()== temp.trim().length()) {
                                //ttlpurchase += Double.parseDouble(buy);
                                //  prcdcts.add(new item(date,namer, "-",buy)); */

                                paint.setColor(Color.BLACK);
                                temp1 = date+"             "+namer+"             -          "+buy+" ";

                                canvas.drawText(temp1, x, y, paint);
                               //  temp2[i]=" ";
                            }
                            else{

                                //  ttlsale += Double.parseDouble(sell);prcdcts.add(new item(date,namer,sell,"-"));
                                temp2=date+"             "+namer+"           "+sell+"            - ";

                                paint.setColor(Color.BLACK);
                                canvas.drawText(temp2, x, y, paint);
                                //  temp2[i]=" ";
                            }


                            y=y+20;
                       }


                    } while (rs.moveToNext());
                  //  prcdcts.add(new item(" ","  ",ttlsale.toString(), ttlpurchase.toString()));


                }

 // ---------------------------------------------------------------------------------------
                // create a new document

              //  canvas.drawText(con, 10, 30, paint);
                //canvas.drawt
                // finish the page
                document.finishPage(page);
// draw text on the graphics object of the page
                // Create Page 2


                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/STOR/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String targetPdf = directory_path+editText.getText().toString()+".pdf";
                File filePath = new File(targetPdf);
                try {
                    document.writeTo(new FileOutputStream(filePath));
                    Toast.makeText(backup.this, "Creating document don't disturb ", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e("main", "error "+e.toString());
                    Toast.makeText(backup.this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
                }
                // close the document
                document.close();

             //  createPdf(main);
            }
        });
    }

    public void please(View v)
    {

    }
    private boolean permissionAlreadyGranted() {

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMREA_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMREA_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted successfully  ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
                boolean showRationale = shouldShowRequestPermissionRationale( Manifest.permission.WRITE_EXTERNAL_STORAGE );
                if (! showRationale) {
                    openSettingsDialog();
                }
            }
        }
    }

    private void openSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(backup.this);
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private static String getMonth(String date) throws ParseException{
        Date d = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
        return monthName;
    }
  /* private void createPdf(String sometext){
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        //  paint.setColor(Color.RED);
        //canvas.drawCircle(50, 50, 30, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(sometext, 10, 30, paint);
        //canvas.drawt
        // finish the page
        document.finishPage(page);
// draw text on the graphics object of the page
        // Create Page 2
        pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 100, paint);
        document.finishPage(page);
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/STOR/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"test2.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }*/
}