package com.example.hi.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Last.db";
    public static final String CONTACTS_TABLE_NAME = "store";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_BUY = "buy";
    public static final String CONTACTS_COLUMN_SELL = "selling";
    public static final String CONTACTS_COLUMN_QUANTITY = "quantity";
    //public static final String CONTACTS_COLUMN_PHONE = "phone";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("create table data " + "(id integer primary key, name text,buy text,selling text, quantity text,barcode text,date text,type text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       // db.execSQL("ALTER TABLE data ADD COLUMN date TEXT");
        //db.execSQL("ALTER TABLE data ADD COLUMN type TEXT");
        onCreate(db);
    }

    public boolean insertContact(String name, String buy, String selling, String quantity,String date,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("buy", buy);
        contentValues.put("selling", selling);
        contentValues.put("quantity", quantity);
        contentValues.put("date", date);
        contentValues.put("type", type);
        db.insert("data", null, contentValues);
        return true;
    }

    public boolean insertCode(String name, String buy, String selling, String quantity,String date,String barcode,String type ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("buy", buy);
        contentValues.put("selling", selling);
        contentValues.put("quantity", quantity);
        contentValues.put("date", date);
        contentValues.put("barcode", barcode);
        contentValues.put("type", type);
        db.insert("data", null, contentValues);
        return true;
    }

    public boolean insertSale(String name, String selling,String date,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("selling", selling);
        contentValues.put("date", date);
        contentValues.put("type", type);
        db.insert("data", null, contentValues);
        return true;
    }




    public Cursor getLast() {
        String selectQuery = "SELECT  * FROM " + "data";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();
        return cursor;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where id = "+id+"", null );
        return res;
    }

    public Cursor getMonth(String mnth) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where date like "+"'%"+mnth+"%'", null );
        return res;
    }


    public Cursor search2(String mnth,String word) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where date like "+"'%"+mnth+"%'"+" and name like"+"'%"+word+"%'"+"COLLATE NOCASE", null );
        return res;
    }


    public Cursor searcher(String word) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where name like"+"'%"+word+"%'"+"COLLATE NOCASE", null );
        return res;
    }
    public Cursor getScan(String[] code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where barcode = "+code+"", null );
        return res;
    }

    public Cursor getProduct() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where type = 'buy'", null );
        return res;
    }

    public Cursor getProductS() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data where type = 'sell'", null );
        return res;
    }
    public Cursor getAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from data ", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,"data");
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String buy, String selling, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("buy", buy);
        contentValues.put("selling", selling);
        contentValues.put("quantity", quantity);
        db.update("data", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deleteContact (String dd) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("data",
                "selling = ? ",
                new String[] { dd });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from store", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}