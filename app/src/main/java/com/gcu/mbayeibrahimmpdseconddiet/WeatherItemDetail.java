package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherItemDetail extends AppCompatActivity {
    private TextView weatherDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_item_detail);

        Intent intent = getIntent();
        String description = intent.getStringExtra("WEATHER_ITEM_DESCRIPTION");
        weatherDescription = findViewById(R.id.weather_description);
        weatherDescription.setText(description);
    }
}