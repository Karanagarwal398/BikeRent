package com.example.karan.bikerent.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karan.bikerent.R;
import com.example.karan.bikerent.adapter.ListingAdapter;
import com.example.karan.bikerent.models.BikeCategoryObject;

import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment {

    private static final String TAG = BookingFragment.class.getSimpleName();

    private RecyclerView bookingRecyclerView;

    public BookingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        bookingRecyclerView = (RecyclerView)view.findViewById(R.id.car_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        bookingRecyclerView.setLayoutManager(gridLayoutManager);
        bookingRecyclerView.setHasFixedSize(true);


        List<BikeCategoryObject> carData = new ArrayList<>();
        carData.add(new BikeCategoryObject(R.drawable.bajaj, "Bajaj"));
        carData.add(new BikeCategoryObject(R.drawable.hero, "Hero"));
        carData.add(new BikeCategoryObject(R.drawable.honda, "Honda"));
        carData.add(new BikeCategoryObject(R.drawable.yahama, "Yahama"));
        carData.add(new BikeCategoryObject(R.drawable.tvs, "Tvs"));
        carData.add(new BikeCategoryObject(R.drawable.ktm, "Ktm"));
        carData.add(new BikeCategoryObject(R.drawable.royal, "Royal Enfield"));

        ListingAdapter mAdapter = new ListingAdapter(getActivity(), carData);
        bookingRecyclerView.setAdapter(mAdapter);
        return view;
    }





}
