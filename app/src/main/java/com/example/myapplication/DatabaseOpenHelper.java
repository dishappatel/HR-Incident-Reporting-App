package com.example.myapplication;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseOpenHelper extends SQLiteAssetHelper
{

    //DATABASE NAME Which is added in asset folder
    //DATABASE filename and variable value should be same.
    private static  final String DATABASE_NAME = "HrIncidentDB.db";
    private static  final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

}
