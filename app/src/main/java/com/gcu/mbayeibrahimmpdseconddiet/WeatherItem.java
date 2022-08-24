package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import java.io.Serializable;

public class WeatherItem implements Serializable {
    private String title;
    private String description;

    WeatherItem() { }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeatherItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
