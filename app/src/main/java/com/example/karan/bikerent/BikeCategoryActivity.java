package com.example.karan.bikerent;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.karan.bikerent.adapter.CategoryAdapter;
import com.example.karan.bikerent.models.BikeCategoryObject;
import com.example.karan.bikerent.models.CarListObject;

import java.util.ArrayList;
import java.util.List;

public class BikeCategoryActivity extends AppCompatActivity {

    private static final String TAG = BikeCategoryObject.class.getSimpleName();

    private RecyclerView carRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_category);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Bike Booking");

        Spinner carOptionSpinner = (Spinner)findViewById(R.id.select_car_option);
        String[] optionSelect = getResources().getStringArray(R.array.car_option);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionSelect); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carOptionSpinner.setAdapter(spinnerArrayAdapter);

        carRecycler = (RecyclerView)findViewById(R.id.cars_in_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        carRecycler.setLayoutManager(linearLayoutManager);
        carRecycler.setHasFixedSize(true);

        CategoryAdapter mAdapter = new CategoryAdapter(BikeCategoryActivity.this, getTestData());
        carRecycler.setAdapter(mAdapter);
    }

    private List<CarListObject> getTestData() {
        List<CarListObject> testData = new ArrayList<>();
        testData.add(new CarListObject(1, "Royal Enfield", R.drawable.royal, "", "", "", "", "", 100));
        testData.add(new CarListObject(1, "Fz", R.drawable.fz, "", "", "", "", "", 100));
        testData.add(new CarListObject(1, "Shine", R.drawable.shine, "", "", "", "", "", 150));
        testData.add(new CarListObject(1, "Gixxer",  R.drawable.gixxer, "", "", "", "", "", 120));
        testData.add(new CarListObject(1, "Access",  R.drawable.access, "", "", "", "", "", 130));
        testData.add(new CarListObject(1, "Activa",  R.drawable.activa, "", "", "", "", "", 200));
        return testData;
    }
}