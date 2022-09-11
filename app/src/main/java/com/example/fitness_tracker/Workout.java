package com.example.fitness_tracker;


public class Workout {
    String user_uid, title, date;

    public Workout() {
    }

    public Workout(String user_uid, String title, String date) {
        this.user_uid = user_uid;
        this.title = title;
        this.date = date;
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
}
