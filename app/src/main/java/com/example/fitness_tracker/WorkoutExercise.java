package com.example.fitness_tracker;

import java.util.List;

public class WorkoutExercise {
    int id, workoutID, exerciseID;
    List<WorkoutSet> workoutSets;
    String exerciseTitle, exerciseImageName;
    TrackingParameters trackingParameters;

    public WorkoutExercise() {
    }

    public WorkoutExercise(int id, int workoutID, int exerciseID, List<WorkoutSet> workoutSets, String exerciseTitle, String exerciseImageName, TrackingParameters trackingParameters) {
        this.id = id;
        this.workoutID = workoutID;
        this.exerciseID = exerciseID;
        this.workoutSets = workoutSets;
        this.exerciseTitle = exerciseTitle;
        this.exerciseImageName = exerciseImageName;
        this.trackingParameters = trackingParameters;
    }

    public int getId() {
        return id;
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public List<WorkoutSet> getWorkoutSets() {
        return workoutSets;
    }

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public String getExerciseImageName() {
        return exerciseImageName;
    }

    public TrackingParameters getTrackingParameters() {
        return trackingParameters;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public void setWorkoutSets(List<WorkoutSet> workoutSets) {
        this.workoutSets = workoutSets;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public void setExerciseImageName(String exerciseImageName) {
        this.exerciseImageName = exerciseImageName;
    }

    public void setTrackingParameters(TrackingParameters trackingParameters) {
        this.trackingParameters = trackingParameters;
    }
}
