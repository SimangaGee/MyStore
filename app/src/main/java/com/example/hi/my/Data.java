package com.example.hi.my;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Data extends AppCompatActivity {
    private DBHelper mydb;
    private Float selll, buyy,sep1,sep2,oct1,oct2,nov1,nov2,dec1,dec2;
    private Button btnSubmit, btnSubmit2;
    private Spinner spinner1, spinner2;
    private EditText find;
    private String first,sec,bob[];
    private Float jan1,jan2,feb1,feb2,mar1,mar2,apr1,apr2,may1,may2,jun1,jun2,jul1,jul2,aug1,aug2;
    private PieChart pieChart;
    private BarChart chat,chart;
    private Integer i,k,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mydb = new DBHelper(this);
         chart = findViewById(R.id.barchart);
        pieChart = findViewById(R.id.piechart);
        find=findViewById(R.id.search);
        chat =findViewById(R.id.barchart1);
        Integer r = 0;
        i =0;
        sep1=sep2=oct1=oct2=nov1=nov2=dec1=dec2 =0.0f;
        jan1=jan2=feb1=feb2=mar1=mar2=apr1=apr2=may1=may2=jun1=jun2=jul1=jul2=aug1=aug2 =0.0f;

        selll = 0.0f;
        buyy = 0.0f;
        ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();
        ArrayList<String> year = new ArrayList<String>();

        ArrayList<String> arrayList = new ArrayList<>();





        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }


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

                first = spinner1.getSelectedItem().toString();
                sec = find.getText().toString().trim();

                Cursor rs = mydb.search2(first, sec);
                if (rs.moveToFirst()) {
                    do {
                        String namer =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                        String sell =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

                        String buy =
                                rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BUY));
                        float seller = Float.parseFloat(sell);
                        String typ = rs.getString(rs.getColumnIndex("type"));

                        String temp = "buy";
                        if (typ.trim().length() == temp.trim().length()) {
                            buyy += Float.parseFloat(buy);


                        } else {

                            selll += Float.parseFloat(sell);
                        }


                    } while (rs.moveToNext());

                }

                ArrayList<Entry> NoOfEmpe = new ArrayList<Entry>();
                ArrayList<String> yeare = new ArrayList<String>();


                NoOfEmpe.add(new Entry(selll, 0));
                NoOfEmpe.add(new Entry(buyy, 1));

                yeare.add("Sales");
                yeare.add("Purchases");

                PieDataSet dataSet = new PieDataSet(NoOfEmpe, " ");
                PieData dataa = new PieData(yeare, dataSet);
                pieChart.setData(dataa);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieChart.animateXY(1000, 1000);


                //============================================================

                bob =new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};



                for (i = 0; i < 12; i++) {

                    Cursor rst = mydb.search2(bob[i],sec);
                    if (rst.moveToFirst()) {
                        while (rst.moveToNext()) {
                            String namer =
                                    rst.getString(rst.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                            String sell =
                                    rst.getString(rst.getColumnIndex(DBHelper.CONTACTS_COLUMN_SELL));

                            String buy =
                                    rst.getString(rst.getColumnIndex(DBHelper.CONTACTS_COLUMN_BUY));
                            float seller = Float.parseFloat(sell);
                            String typ = rst.getString(rst.getColumnIndex("type"));

                            String temp = "buy";
                            String tempe = "sell";
                            if (typ.equals(temp) ) {
                                if (bob[i].equals("Jan")) {
                                    jan1= jan1+Float.parseFloat(buy);
                                } else if (bob[i].equals("Feb")) {
                                    feb1 =feb1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("Apr")) {
                                    mar1 = mar1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("May")) {
                                    may1 = may1+Float.parseFloat(buy);
                                } else if (bob[i].equals("Jun")) {
                                    jun1 = jun1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("Jul")) {
                                    jul1 =jul1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("Aug")) {
                                    aug1 = aug1+Float.parseFloat(buy);
                                } else if (bob[i].equals("Sep")) {
                                    sep1 =sep1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("Oct")) {
                                    oct1 =oct1 + Float.parseFloat(buy);
                                } else if (bob[i].equals("Nov")) {
                                    nov1 =nov1+ Float.parseFloat(buy);
                                } else if (bob[i].equals("Dec")) {
                                    dec1 = dec1+ Float.parseFloat(buy);
                                }

                            }
                            else if(typ.equals(tempe)){

                                    if (bob[i].equals("Jan")) {
                                        jan2 =jan2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Feb")) {
                                        feb2 =feb2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Apr")) {
                                        mar2 =mar2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("May")) {
                                        may2 =may2+Float.parseFloat(sell);
                                    } else if (bob[i].equals("Jun")) {
                                        jun2 =jun2+Float.parseFloat(sell);
                                    } else if (bob[i].equals("Jul")) {
                                        jul2 =jul2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Aug")) {
                                        aug2 =aug2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Sep")) {
                                        sep2 = sep2+Float.parseFloat(sell);
                                    } else if (bob[i].equals("Oct")) {
                                        oct2 =oct2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Nov")) {
                                        nov2 =nov2+ Float.parseFloat(sell);
                                    } else if (bob[i].equals("Dec")) {
                                        dec2 =dec2+ Float.parseFloat(sell);
                                    }


                            }
                        }


                        }
                    }



                ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();

                NoOfEmp.add(new BarEntry(jan1, 0));
                NoOfEmp.add(new BarEntry(feb1, 1));
                NoOfEmp.add(new BarEntry(mar1, 2));
                NoOfEmp.add(new BarEntry(apr1, 3));
                NoOfEmp.add(new BarEntry(may1, 4));
                NoOfEmp.add(new BarEntry(jun1, 5));
                NoOfEmp.add(new BarEntry(jul1, 6));
                NoOfEmp.add(new BarEntry(aug1, 7));
                NoOfEmp.add(new BarEntry(sep1, 8));
                NoOfEmp.add(new BarEntry(oct1, 9));
                NoOfEmp.add(new BarEntry(nov1, 10));
                NoOfEmp.add(new BarEntry(dec1, 11));


                ArrayList<String> year = new ArrayList<String>();

                year.add(bob[0]);
                year.add(bob[1]);
                year.add(bob[2]);
                year.add(bob[3]);
                year.add(bob[4]);
                year.add(bob[5]);
                year.add(bob[6]);
                year.add(bob[7]);
                year.add(bob[8]);
                year.add(bob[9]);
                year.add(bob[10]);
                year.add(bob[11]);

                BarDataSet bardataset = new BarDataSet(NoOfEmp, "Purchases");
                chat.animateY(5000);
                BarData data = new BarData(year, bardataset);
                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                chat.setData(data);


                //-----------------------------------------------

                ArrayList<BarEntry> NoOfEm = new ArrayList<BarEntry>();

                NoOfEm.add(new BarEntry(jan2, 0));
                NoOfEm.add(new BarEntry(feb2, 1));
                NoOfEm.add(new BarEntry(mar2, 2));
                NoOfEm.add(new BarEntry(apr2, 3));
                NoOfEm.add(new BarEntry(may2, 4));
                NoOfEm.add(new BarEntry(jun2, 5));
                NoOfEm.add(new BarEntry(jul2, 6));
                NoOfEm.add(new BarEntry(aug2, 7));
                NoOfEm.add(new BarEntry(sep2, 8));
                NoOfEm.add(new BarEntry(oct2, 9));
                NoOfEm.add(new BarEntry(nov2, 10));
                NoOfEm.add(new BarEntry(dec2, 11));


                ArrayList<String> yea = new ArrayList<String>();

                yea.add(bob[0]);
                yea.add(bob[1]);
                yea.add(bob[2]);
                yea.add(bob[3]);
                yea.add(bob[4]);
                yea.add(bob[5]);
                yea.add(bob[6]);
                yea.add(bob[7]);
                yea.add(bob[8]);
                yea.add(bob[9]);
                yea.add(bob[10]);
                yea.add(bob[11]);

                BarDataSet bardatase = new BarDataSet(NoOfEmp, "Sales");
                chart.animateY(5000);
                BarData dat = new BarData(yea, bardatase);
                bardatase.setColors(ColorTemplate.COLORFUL_COLORS);
                chart.setData(dat);


            }
        });
    }



}



