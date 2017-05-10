package com.example.ssjianyu.sqlcontack;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ssjianyu.sqlcontack.DbContent.COL1;
import static com.example.ssjianyu.sqlcontack.DbContent.COL2;
import static com.example.ssjianyu.sqlcontack.DbContent.COL3;
import static com.example.ssjianyu.sqlcontack.DbContent.COL4;
import static com.example.ssjianyu.sqlcontack.DbContent.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "people.db";
    private final static int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String Query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT);";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name,String phone,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,phone);
        contentValues.put(COL4, email);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updateData(String id,String name,String phone,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,phone);
        contentValues.put(COL4, email);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String [] {id});
                //contentValues, "COL1 = ?",new String [] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
         return db.delete(TABLE_NAME," ID = ?",new String []{id});
    }
}
