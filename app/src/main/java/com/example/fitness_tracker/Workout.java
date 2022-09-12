package com.example.fitness_tracker;


public class Workout implements Comparable<Workout> {
    String user_uid, date;
    int sets, exercises;

    public Workout() {
    }

    public Workout(String user_uid, String date, int sets, int exercises) {
        this.user_uid = user_uid;
        this.date = date;
        this.sets = sets;
        this.exercises = exercises;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public String getDate() {
        return date;
    }

    public int getSets() {
        return sets;
    }

    public int getExercises() {
        return exercises;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setExercises(int exercises) {
        this.exercises = exercises;
    }

    @Override
    public int compareTo(Workout workout) {
        if (getDate() == null || workout.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(workout.getDate());
    }
}
