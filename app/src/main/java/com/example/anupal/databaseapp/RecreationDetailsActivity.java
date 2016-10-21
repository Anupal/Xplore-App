package com.example.anupal.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecreationDetailsActivity extends AppCompatActivity {

    LocationDatabaseHelper helper;
    List<LocationDatabaseModel> dbList;
    int position;
    TextView tvName,tvDescription,tvContact_number,tvAddress,tvType,tvRating;
    Button btnRate;
    EditText etRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recreation_details);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        tvName =(TextView)findViewById(R.id.tvName);
        tvDescription =(TextView)findViewById(R.id.tvDescription);
        tvContact_number =(TextView)findViewById(R.id.tvContact_number);
        tvAddress =(TextView)findViewById(R.id.tvAddress);
        tvType =(TextView)findViewById(R.id.tvType);
        tvRating =(TextView)findViewById(R.id.tvRating);
        btnRate = (Button)findViewById(R.id.btnRate) ;
        etRate = (EditText)findViewById(R.id.etRate);

        helper = new LocationDatabaseHelper(this);
        dbList= new ArrayList<LocationDatabaseModel>();
        dbList = helper.getRecreationFromDB();

        if(dbList.size()>0){
            String name = dbList.get(position).getName();
            String adddress = dbList.get(position).getAddress();
            String contact_number = dbList.get(position).getContactNo();
            String rating = dbList.get(position).getRating();
            String type = dbList.get(position).getType();
            String description = dbList.get(position).getDescription();


            tvName.setText(name);
            tvDescription.setText(description);
            tvContact_number.setText(contact_number);
            tvAddress.setText(adddress);
            tvType.setText(type);
            tvRating.setText(rating);
        }

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newRatingValue = etRate.getText().toString();
                String oldRatingValue = tvRating.getText().toString();

                float nrval = Float.parseFloat(newRatingValue);
                float orval = Float.parseFloat(oldRatingValue);
                nrval = (nrval + orval)/2;
                newRatingValue = String.valueOf(nrval);
                helper.updateRating(tvContact_number.getText().toString(),newRatingValue);
                tvRating.setText(newRatingValue);
            }
        });


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