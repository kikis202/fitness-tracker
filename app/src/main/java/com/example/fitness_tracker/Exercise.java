package com.example.fitness_tracker;

public class Exercise {
    String title, description, image_name;
    TrackingParameters trackingParameters;

    public Exercise() {
    }

    public Exercise(String title, String description, String image_name, TrackingParameters trackingParameters) {
        this.title = title;
        this.description = description;
        this.image_name = image_name;
        this.trackingParameters = trackingParameters;
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

    public TrackingParameters getTrackingParameters() {
        return trackingParameters;
    }
}
