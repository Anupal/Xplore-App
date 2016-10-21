package com.example.anupal.databaseapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anupal on 21/10/16.
 */

public class FoodLocationRecyclerAdapter extends RecyclerView.Adapter<FoodLocationRecyclerAdapter.ViewHolder> {

    static List<LocationDatabaseModel> dbList;
    static Context context;
    FoodLocationRecyclerAdapter(Context context, List<LocationDatabaseModel> dbList ){
        this.dbList = new ArrayList<LocationDatabaseModel>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public FoodLocationRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_row_location, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodLocationRecyclerAdapter.ViewHolder holder, int position) {

        holder.name.setText(dbList.get(position).getName());
        holder.rating.setText(dbList.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name,rating;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.rvnamel);
            rating = (TextView)itemLayoutView.findViewById(R.id.rvratingl);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,FoodDetailsActivity.class);

            Bundle extras = new Bundle();
            extras.putInt("position",getAdapterPosition());
            intent.putExtras(extras);


            context.startActivity(intent);
        }
    }
}