package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DayWeatherRecyclerAdapter extends RecyclerView.Adapter<DayWeatherRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<WeatherItem> weatherItems;
    private LayoutInflater layoutInflater;

    DayWeatherRecyclerAdapter(Context context, List<WeatherItem> weatherItems) {
        this.context = context;
        this.weatherItems = weatherItems;
        this.layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_day_weather, parent, false);
        return new DayWeatherRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WeatherItem weatherItem = weatherItems.get(position);
        holder.title.setText(weatherItem.getTitle());
        holder.itemPosition = position;
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public int itemPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.weather_item_title);
            this.itemPosition = 0;
            itemView.setOnClickListener((View v) -> {
                Intent intent = new Intent(context, WeatherItemDetail.class);
                intent.putExtra("WEATHER_ITEM_DESCRIPTION", weatherItems.get(this.itemPosition).getDescription());
                context.startActivity(intent);
            });
        }
    }
}
