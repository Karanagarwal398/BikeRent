package com.example.karan.bikerent.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karan.bikerent.R;


public class ListingViewHolder extends RecyclerView.ViewHolder {

    public ImageView carImage;
    public TextView carName;
    public View view;


    public ListingViewHolder(View itemView) {
        super(itemView);

        carImage = (ImageView)itemView.findViewById(R.id.car_category_image);
        carName = (TextView)itemView.findViewById(R.id.car_category_name);
        view = itemView;
    }
}