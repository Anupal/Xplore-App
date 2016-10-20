package com.example.anupal.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anupal on 21/10/16.
 */

public class UserHomeActivity extends AppCompatActivity {

    EditText etNamel,etAddressl,etDescriptionl,etTypel,etContactNumberl;
    Button btnAdd,btnFood,btnRecreation;
    LocationDatabaseHelper helper;
    List<LocationDatabaseModel> dbList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        dbList= new ArrayList<LocationDatabaseModel>();
        etNamel = (EditText)findViewById(R.id.etNamel);
        etAddressl = (EditText)findViewById(R.id.etAddressl);
        etDescriptionl = (EditText)findViewById(R.id.etDescriptionl);
        etTypel = (EditText)findViewById(R.id.etTypel);
        etContactNumberl = (EditText)findViewById(R.id.etContactNumberl);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnFood = (Button)findViewById(R.id.btnFood);
        btnRecreation = (Button)findViewById(R.id.btnRecreation);

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHomeActivity.this, FoodActivity.class));

                // startActivity(new Intent(SignUpActivity.this, UserDetailsActivity.class));

            }
        });

        btnRecreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHomeActivity.this, RecreationActivity.class));

                // startActivity(new Intent(SignUpActivity.this, UserDetailsActivity.class));

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etNamel.getText().toString();
                String address = etAddressl.getText().toString();
                String contact_number = etContactNumberl.getText().toString();
                String description = etDescriptionl.getText().toString();
                String type = etTypel.getText().toString();
                if(name.equals("") || address.equals("") || contact_number.equals("") ||description.equals("") ||type.equals("")){
                    Toast.makeText(UserHomeActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }else {
                    helper = new LocationDatabaseHelper(UserHomeActivity.this);
                    helper.insertIntoDB(name, address, contact_number, description, type);
                }
                etNamel.setText("");
                etAddressl.setText("");
                etContactNumberl.setText("");
                etDescriptionl.setText("");
                etTypel.setText("");

                Toast.makeText(UserHomeActivity.this, "Location Created", Toast.LENGTH_LONG);

            }
        });

    }
}
