package com.example.anupal.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    EditText etName,etPassword,etcontact_number,etEmail;
    Button btnSubmit,btngetdata;
    UserDatabaseHelper helper;
    List<UserDatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbList= new ArrayList<UserDatabaseModel>();
        etName = (EditText)findViewById(R.id.etName);
        etcontact_number = (EditText)findViewById(R.id.etcontact_number);
        etPassword =(EditText)findViewById(R.id.etPassword);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SecondActivity.class));

                // startActivity(new Intent(SignUpActivity.this, DetailsActivity.class));

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String contact_number = etcontact_number.getText().toString();
                String password = etPassword.getText().toString();

                if(name.equals("") || email.equals("") || contact_number.equals("") ||password.equals("")){
                    Toast.makeText(SignUpActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }else {
                    helper = new UserDatabaseHelper(SignUpActivity.this);
                    helper.insertIntoDB(name, email, contact_number, password);
                }
                etName.setText("");
                etPassword.setText("");
                etcontact_number.setText("");
                etEmail.setText("");

                Toast.makeText(SignUpActivity.this, "insert value", Toast.LENGTH_LONG);

            }
        });

    }


}