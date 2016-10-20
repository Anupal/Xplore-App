package com.example.anupal.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {
    UserDatabaseHelper helper;
    List<UserDatabaseModel> dbList;
    int position;
    TextView tvname,tvemail,tvcontact_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_details);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.name);
        tvemail =(TextView)findViewById(R.id.email);
        tvcontact_number =(TextView)findViewById(R.id.contact_number);
        helper = new UserDatabaseHelper(this);
        dbList= new ArrayList<UserDatabaseModel>();
        dbList = helper.getDataFromDB();

        if(dbList.size()>0){
            String name = dbList.get(position).getName();
            String email = dbList.get(position).getEmail();
            String contact_number = dbList.get(position).getContactNumber();
            tvname.setText(name);
            tvemail.setText(email);
            tvcontact_number.setText(contact_number);
        }

        Toast.makeText(UserDetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}