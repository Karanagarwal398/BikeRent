package com.example.karan.bikerent.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karan.bikerent.BikeCategoryActivity;
import com.example.karan.bikerent.R;
import com.example.karan.bikerent.models.BikeCategoryObject;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingViewHolder>{

    private static final String TAG = ListingAdapter.class.getSimpleName();

    private Context context;

    private List<BikeCategoryObject> carList;


    public ListingAdapter(Context context, List<BikeCategoryObject> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_category_layout, parent, false);
        return new ListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        BikeCategoryObject carCategoryObject = carList.get(position);
//        if (holder.carName.toString()=="Bajaj") {
//
//            Intent i = new Intent(UserActivity.this, ScrapList.class);
//            context.startActivity(i);
//        }

         holder.carName.setText(carCategoryObject.getImageName());
         holder.carImage.setImageResource(carCategoryObject.getImagePath());
        //holder.carImage
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carCategoryIntent = new Intent(context, BikeCategoryActivity.class);
                context.startActivity(carCategoryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
