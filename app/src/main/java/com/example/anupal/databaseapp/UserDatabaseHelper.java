package com.example.anupal.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="user";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "usereg";
    private static final String U_TABLE = "create table "+USER_TABLE +"(name TEXT,email TEXT primary key,contact_number TEXT,password TEXT)";

    Context context;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(U_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        // Create tables again
        onCreate(db);
    }
    /* Insert into database*/
    public void insertIntoDB(String name,String email,String roll,String password){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("contact_number", roll);
        values.put("password", password);

        // 3. insert
        db.insert(USER_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<UserDatabaseModel> getDataFromDB(){
        List<UserDatabaseModel> modelList = new ArrayList<UserDatabaseModel>();
        String query = "select * from "+USER_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                UserDatabaseModel model = new UserDatabaseModel();
                model.setName(cursor.getString(0));
                model.setEmail(cursor.getString(1));
                model.setContactNumber(cursor.getString(2));
                model.setPassword(cursor.getString(3));


                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("user data", modelList.toString());


        return modelList;
    }

    /* Get password for a username for login */
    public String checkPassword(String email)
    {
        String query = "select password from "+USER_TABLE + " where email=" + email;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(USER_TABLE, new String[]{"PASSWORD"}, " EMAIL=?", new String[]{email}, null, null, null);
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

    /*delete a row from database*/

    public void deleteARow(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(USER_TABLE, "email" + " = ?", new String[] { email });
        db.close();
    }

}