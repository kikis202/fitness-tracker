package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutDetailsActivity extends AppCompatActivity {
    LinearLayout addExerciseButton, deleteWorkoutButton;
    TextView saveWorkoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        TextView firstView = (TextView) findViewById(R.id.firstView);


        addExerciseButton = findViewById(R.id.add_exercise_button);
        deleteWorkoutButton = findViewById(R.id.delete_workout_button);
        saveWorkoutButton = findViewById(R.id.save_workout_button);

        addExerciseButton.setOnClickListener(v -> openExerciseList());

    }

    public void openExerciseList() {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        startActivity(intent);
    }


}