package com.example.anupal.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by anupal on 20/10/16.
 */

public class LoginActivity extends AppCompatActivity {

    EditText etPassword, etEmail;
    Button btnSignUp, btnLogin;
    UserDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword =(EditText)findViewById(R.id.etPassword);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                helper = new UserDatabaseHelper(LoginActivity.this);
                // fetch the Password form database for respective user name
                String storedPassword = helper.checkPassword(email);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intentHome=new Intent(getApplicationContext(),UserHomeActivity.class);
                    startActivity(intentHome);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
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
