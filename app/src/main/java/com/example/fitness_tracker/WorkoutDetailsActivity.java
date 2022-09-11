package com.example.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class WorkoutDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<WorkoutExercise> workoutExerciseList;

    DBHelper dbHelper;

    LinearLayout addExerciseButton, deleteWorkoutButton, emptyWorkoutLayout;
    TextView saveWorkoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        addExerciseButton = findViewById(R.id.add_exercise_button);
        deleteWorkoutButton = findViewById(R.id.delete_workout_button);
        saveWorkoutButton = findViewById(R.id.save_workout_button);
        emptyWorkoutLayout = findViewById(R.id.get_started_section);

        addExerciseButton.setOnClickListener(v -> openExerciseList());
        saveWorkoutButton.setOnClickListener(v -> saveWorkout());
        deleteWorkoutButton.setOnClickListener(v -> deleteWorkout());

        recyclerView = findViewById(R.id.rv_workoutExercises);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        int workoutID = SaveSharedPreference.getActiveWorkoutID(WorkoutDetailsActivity.this);
        dbHelper = new DBHelper(WorkoutDetailsActivity.this);
        workoutExerciseList = dbHelper.getWorkoutExerciseList(workoutID);

        mAdapter = new WorkoutDetailsAdapter(workoutExerciseList, WorkoutDetailsActivity.this);
        recyclerView.setAdapter(mAdapter);

        activeExercises();
    }

    public void openExerciseList(){
        Intent intent = new Intent(WorkoutDetailsActivity.this, ExerciseListActivity.class);
        startActivity(intent);
    }

    public void saveWorkout() {
        SaveSharedPreference.setActiveWorkoutID(WorkoutDetailsActivity.this, 0);
        startActivity(new Intent(WorkoutDetailsActivity.this, MainActivity.class));
        finish();
    }

    public void deleteWorkout() {
        // TODO: delete workout, workoutExercise, workoutSets from database
        SaveSharedPreference.setActiveWorkoutID(WorkoutDetailsActivity.this, 0);
        startActivity(new Intent(WorkoutDetailsActivity.this, MainActivity.class));
        finish();
    }

    public void activeExercises() {
        if (workoutExerciseList.size() > 0) {
            emptyWorkoutLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyWorkoutLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}