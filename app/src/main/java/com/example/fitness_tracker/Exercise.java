package com.example.fitness_tracker;

public class Exercise {
    int id, trackingTypeId;
    String title, description, image_name;


    public Exercise() {
    }

    public Exercise(int id, int trackingTypeId, String title, String description, String image_name) {
        this.id = id;
        this.trackingTypeId = trackingTypeId;
        this.title = title;
        this.description = description;
        this.image_name = image_name;
    }

    public int getId() {
        return id;
    }

    public int getTrackingTypeId() {
        return trackingTypeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrackingTypeId(int trackingTypeId) {
        this.trackingTypeId = trackingTypeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
