package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<WeatherInfo> locations;
    private LayoutInflater layoutInflater;

    LocationRecyclerAdapter(Context context, List<WeatherInfo> locations) {
        this.context = context;
        this.locations = locations;
        this.layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_location, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherInfo weatherInfo = locations.get(position);
        holder.title.setText(weatherInfo.getTitle());
        holder.description.setText(weatherInfo.getDescription());
        holder.items.setLayoutManager(new LinearLayoutManager(context));
        holder.items.setAdapter(new DayWeatherRecyclerAdapter(context, weatherInfo.getItems()));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        private TextView description;
        private RecyclerView items;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.location_title);
            this.description = itemView.findViewById(R.id.location_description);
            this.items = itemView.findViewById(R.id.weather_items);
        }
    }
}
