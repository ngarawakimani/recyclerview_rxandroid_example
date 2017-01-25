package com.wakimani.recyclerviewrxandroidexample;

/**
 * Created by wa kimani on 30/12/2016.
 */
public class ItemsList {
    public String image;
    public String title;
    public String description;
    public String station;

    public ItemsList(String image, String title, String description, String station) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.station = station;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
