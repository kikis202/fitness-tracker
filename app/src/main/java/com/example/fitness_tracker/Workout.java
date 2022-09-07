package com.example.fitness_tracker;

import java.util.HashMap;
import java.util.Map;

public class Workout {
    String user_uid, title, date;
    Map<String, Boolean> exercises = new HashMap<>();

    public Workout() {
    }

    public Workout(String user_uid, String title, String date) {
        this.user_uid = user_uid;
        this.title = title;
        this.date = date;
    }

    public void addExercise(String exerciseId) {
        exercises.put(exerciseId, true);
    }

    public String getUser_uid() {
        return user_uid;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Boolean> getExercises() {
        return exercises;
    }
}
