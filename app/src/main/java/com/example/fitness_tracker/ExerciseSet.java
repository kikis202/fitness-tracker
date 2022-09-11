package com.example.fitness_tracker;

import java.util.HashMap;
import java.util.Map;

public class ExerciseSet {
    String exercise_id, workout_id, user_uid;
    Map<String, Integer> tracking = new HashMap<>();

    public ExerciseSet() {
    }

    public ExerciseSet(String exercise_id, String workout_id, String user_uid, int reps, int distance, int time, float weight) {
        this.exercise_id = exercise_id;
        this.workout_id = workout_id;
        this.user_uid = user_uid;
    }

    public void addTracking(String tracker, Integer value) {
        tracking.put(tracker, value);
    }

    public String getExercise_id() {
        return exercise_id;
    }

    public String getWorkout_id() {
        return workout_id;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public Map<String, Integer> getTracking() {
        return tracking;
    }
}
