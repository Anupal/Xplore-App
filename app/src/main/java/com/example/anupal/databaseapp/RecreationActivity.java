package com.example.anupal.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RecreationActivity extends AppCompatActivity {
    LocationDatabaseHelper helper;
    List<LocationDatabaseModel> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recreation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        helper = new LocationDatabaseHelper(this);
        dbList= new ArrayList<LocationDatabaseModel>();
        dbList = helper.getRecreationFromDB();


        mRecyclerView = (RecyclerView)findViewById(R.id.recycleviewr);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new LocationRecyclerAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);

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