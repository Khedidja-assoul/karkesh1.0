package com.example.karkesh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DB_Manager extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "ff.db";
    private static final int DATABASE_VERSION = 1;

    public DB_Manager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public boolean existCodes(String code1, String code2){
        String statement = "SELECT * from mytable where (Code_1 = '"+code1+"' and Code_2 = '"+code2+"')or (Code_1 = '"+code2+"' and Code_2 = '"+code1+"')";
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery(statement,null);

        return cursor.moveToFirst();
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}



}



