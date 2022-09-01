package com.example.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutDetailsActivity extends AppCompatActivity {
    LinearLayout addExerciseButton, deleteWorkoutButton;
    TextView saveWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        addExerciseButton = (LinearLayout) findViewById(R.id.add_exercise_button);
        deleteWorkoutButton = (LinearLayout) findViewById(R.id.delete_workout_button);
        saveWorkoutButton = findViewById(R.id.save_workout_button);
    }
}