package com.example.fitness_tracker;

public class TrackingParameters {
    boolean reps, weight, distance, time;

    public TrackingParameters() {
    }

    public TrackingParameters(boolean reps, boolean weight, boolean distance, boolean time) {
        this.reps = reps;
        this.weight = weight;
        this.distance = distance;
        this.time = time;
    }

    public boolean isReps() {
        return reps;
    }

    public boolean isWeight() {
        return weight;
    }

    public boolean isDistance() {
        return distance;
    }

    public boolean isTime() {
        return time;
    }
}
