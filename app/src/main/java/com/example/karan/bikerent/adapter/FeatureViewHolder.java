package com.example.karan.bikerent.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.karan.bikerent.R;


public class FeatureViewHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public TextView detail;

    public FeatureViewHolder(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.car_feature_title);
        detail = (TextView)itemView.findViewById(R.id.car_price_title);

    }
}
