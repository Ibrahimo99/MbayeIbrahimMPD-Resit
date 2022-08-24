package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    private RecyclerView locationsView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.e("MyTag","in onCreate");
        Log.e("MyTag","after startButton");
        // More Code goes here

        Intent i = getIntent();
        List<WeatherInfo> list = (List<WeatherInfo>) i.getSerializableExtra("com.example.maha.EXTRA_LOCATION_WEATHERS");

        locationsView = (RecyclerView) findViewById(R.id.listLocations);
        locationsView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        locationsView.setAdapter(new LocationRecyclerAdapter(this, list));
    }
}
