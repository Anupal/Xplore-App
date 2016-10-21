package com.example.anupal.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by anupal on 20/10/16.
 */

public class LoginActivity extends AppCompatActivity {

    EditText etPassword, etEmail;
    Button btnSignUp, btnLogin;
    UserDatabaseHelper helper;
    AdminDatabaseHelper helper2;
    CheckBox chkb;
    boolean checkFlag =false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword =(EditText)findViewById(R.id.etPassword);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        chkb = (CheckBox)findViewById(R.id.checkBox);
        helper2 = new AdminDatabaseHelper(LoginActivity.this);
        helper2.insertIntoDB("Anupal","anupal@gmail.com","abcd");
        helper2.insertIntoDB("Sayak","sayak@gmail.com","cdef");
        helper2.insertIntoDB("Maharshi","maharshi@gmail.com","abcd");
        chkb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                checkFlag = !checkFlag;
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(!checkFlag) {
                    helper = new UserDatabaseHelper(LoginActivity.this);
                    // fetch the Password form database for respective user name
                    String storedPassword = helper.checkPassword(email);

                    // check if the Stored password matches with  Password entered by user
                    if (password.equals(storedPassword)) {
                        Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent intentHome = new Intent(getApplicationContext(), UserHomeActivity.class);
                        startActivity(intentHome);
                    } else {
                        Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                }

                if(checkFlag) {
                    helper2 = new AdminDatabaseHelper(LoginActivity.this);
                    // fetch the Password form database for respective user name
                    String storedPassword = helper2.checkPassword(email);

                    // check if the Stored password matches with  Password entered by user
                    if (password.equals(storedPassword)) {
                        Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent intentHome = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(intentHome);
                    } else {
                        Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUp=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });
    }



}
