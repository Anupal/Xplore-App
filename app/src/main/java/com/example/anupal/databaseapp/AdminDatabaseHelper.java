package com.example.anupal.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by anupal on 21/10/16.
 */

public class AdminDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="admin";
    private static final int DATABASE_VERSION = 1;
    private static final String ADMIN_TABLE = "adminreg";
    private static final String U_TABLE = "create table "+ADMIN_TABLE +"(name TEXT,email TEXT primary key,password TEXT)";

    Context context;

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(U_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void insertIntoDB(String name,String email,String password){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);

        // 3. insert
        db.insert(ADMIN_TABLE, null, values);
        // 4. close
        db.close();
    }

    public String checkPassword(String email)
    {
        String query = "select password from "+ADMIN_TABLE + " where email=" + email;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(ADMIN_TABLE, new String[]{"PASSWORD"}, " EMAIL=?", new String[]{email}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(0); //cursor.getColumnIndex("PASSWORD")
        cursor.close();
        return password;
    }


}
