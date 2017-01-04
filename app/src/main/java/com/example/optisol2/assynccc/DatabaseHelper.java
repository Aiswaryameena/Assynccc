package com.example.optisol2.assynccc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by optisol2 on 29-12-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "myfirstdb.db";
    public static final String TBL_NAME = "Contacts";
    public static final String TBL_CNTCTNAME = "contact_name";
    public static final String TBL_PHNO = "Phone_no";
    public static final String TBL_EMAIL="email";
    public static final String TBL_CITY="madurai";
    public static final String TBL_ID = "id";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Contacts" + "(id integer primary key,contact_name text,Phone_no integer,email text,city text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Contacts");
        onCreate(db);
    }

    public boolean insertContact(String name, String phno,String email,String city) {
        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("contact_name", name);
//        contentValues.put("Phone_no", phno);
//        db.insert("Contacts", null, contentValues);
//        return true;
        db.execSQL("INSERT INTO " + TBL_NAME + "(" + TBL_CNTCTNAME + "," + TBL_PHNO + "," + TBL_EMAIL +"," + TBL_CITY +  ") VALUES('" + name + "','" + phno +"','" + email +"','" + city + "')");
        db.close();
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Contacts where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TBL_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String TBL_CNTCTNAME, String TBL_PHNO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact_name", TBL_CNTCTNAME);
        contentValues.put("Phone_no", TBL_PHNO);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Contacts", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(TBL_CNTCTNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
