package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;



    private DatabaseAccess(Context context){
       // this.openHelper = new DatabaseOpenHelper(context);
            this.openHelper = new DatabaseOpenHelper(context);
    }

    //Singleton class to access database and create database if does not exists
    public  static  DatabaseAccess getInstance(Context context){

        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return  instance;
    }

    //Open Database connection
    public  void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public  void close()
    {
        if (db!= null)
        {
            this.db.close();
        }
    }

    //Method return cursor with all body parts from db.
    public Cursor getBodyPartsList(){
        Cursor c = db.rawQuery("select * from tbl_BodyParts",null);
        return  c;
    }


    //|Insert Incident records in database.
    public  void insertIncidentRecord(String strQuery)
    {
        try
        {
            db.execSQL(strQuery);
        }
        catch (Exception e)
        {
            Log.d("0",e.getMessage().toString());
        }
    }
    //Get All incident Records ffrom db and display in view incident activity****
    public Cursor getAllIncidentHistory()
    {
        Cursor c = db.rawQuery("select * from tbl_IncidentHistory",null);
//        if(c != null)
//        {
//            c.moveToFirst();
//        }
        return  c;
    }
}
