package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import java.io.Serializable;
import java.util.List;

public class WeatherInfo implements Serializable {
    private String title;
    private String description;
    private List<WeatherItem> items;

    WeatherInfo() {}

    public List<WeatherItem> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItems(List<WeatherItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}
