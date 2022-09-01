package com.example.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WorkoutDetailsActivity extends AppCompatActivity {
    LinearLayout addExerciseButton, deleteWorkoutButton;
    TextView saveWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        addExerciseButton = findViewById(R.id.add_exercise_button);
        deleteWorkoutButton = findViewById(R.id.delete_workout_button);
        saveWorkoutButton = findViewById(R.id.save_workout_button);

        //addExerciseButton.setOnClickListener(v -> testFunc());
    }
/*
    void testFunc(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        startActivity(new Intent(WorkoutDetailsActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

 */
}