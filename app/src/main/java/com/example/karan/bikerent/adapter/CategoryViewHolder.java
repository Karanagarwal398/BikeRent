package com.example.karan.bikerent.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.karan.bikerent.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder{

    public TextView carType;
    public TextView carName;
    public ImageView carImage;
    public RatingBar ratingBar;
    public TextView price;
    public View view;


    public CategoryViewHolder(View itemView) {
        super(itemView);

        carType = (TextView)itemView.findViewById(R.id.car_type);
        carName = (TextView)itemView.findViewById(R.id.car_name);
        carImage= (ImageView)itemView.findViewById(R.id.car_category_image);
        ratingBar = (RatingBar)itemView.findViewById(R.id.rating_bar);
        price = (TextView)itemView.findViewById(R.id.price_per_day);
        view = itemView;
    }
}
