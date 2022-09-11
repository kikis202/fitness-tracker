package com.example.fitness_tracker;


public class WorkoutSet {
    int id, workoutExerciseID, reps, weight, distance, time;

    public WorkoutSet() {
    }

    public WorkoutSet(int id, int workoutExerciseID, int reps, int weight, int distance, int time) {
        this.id = id;
        this.workoutExerciseID = workoutExerciseID;
        this.reps = reps;
        this.weight = weight;
        this.distance = distance;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getWorkoutExerciseID() {
        return workoutExerciseID;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkoutExerciseID(int workoutExerciseID) {
        this.workoutExerciseID = workoutExerciseID;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
