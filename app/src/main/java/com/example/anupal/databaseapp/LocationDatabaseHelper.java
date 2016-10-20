package com.example.anupal.databaseapp;

/**
 * Created by anupal on 21/10/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocationDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="location";
    private static final int DATABASE_VERSION = 1;
    private static final String LOCATION_TABLE = "locreg";
    private static final String L_TABLE = "create table "+LOCATION_TABLE +"(name TEXT,address TEXT,contactNumber TEXT primary key,description TEXT,type TEXT,rating TEXT)";

    Context context;

    public LocationDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(L_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);

        // Create tables again
        onCreate(db);
    }

    // Insert New Values
    public void insertIntoDB(String name,String address,String contactNumber,String description,String type){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("contactNumber", contactNumber);
        values.put("description", description);
        values.put("type", type);
        values.put("rating", "0");

        // 3. insert
        db.insert(LOCATION_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "Location Created", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    public List<LocationDatabaseModel> getFoodFromDB(){
        List<LocationDatabaseModel> modelList = new ArrayList<LocationDatabaseModel>();
        String query = "select * from "+LOCATION_TABLE + " WHERE type=? ORDER BY RATING DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,new String[]{"Food"});

        if (cursor.moveToFirst()){
            do {
                LocationDatabaseModel model = new LocationDatabaseModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setContactNo(cursor.getString(2));
                model.setDescription(cursor.getString(3));
                model.setType(cursor.getString(4));
                model.setRating(cursor.getString(5));
                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("user data", modelList.toString());


        return modelList;
    }

    public List<LocationDatabaseModel> getRecreationFromDB(){
        List<LocationDatabaseModel> modelList = new ArrayList<LocationDatabaseModel>();
        String query = "select * from "+LOCATION_TABLE + " WHERE type=? ORDER BY RATING DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,new String[]{"Recreation"});

        if (cursor.moveToFirst()){
            do {
                LocationDatabaseModel model = new LocationDatabaseModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setContactNo(cursor.getString(2));
                model.setDescription(cursor.getString(3));
                model.setType(cursor.getString(4));
                model.setRating(cursor.getString(5));
                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("user data", modelList.toString());


        return modelList;
    }

    public void deleteARow(String contactNumber){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(LOCATION_TABLE, "contactNumber" + " = ?", new String[] { contactNumber });
        db.close();
    }

}
