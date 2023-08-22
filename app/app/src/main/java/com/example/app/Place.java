package com.example.app;

import java.io.Serializable;

public class Place implements Serializable {
    private Integer placeId;
    private String placeName;
    private String description;

    public Place(String placeName, String description, Integer placeId) {
        this.placeName = placeName;
        this.description = description;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Place name: " +placeName+". Description: "+description;
    }
}
