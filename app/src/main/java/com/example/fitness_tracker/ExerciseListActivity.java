package com.example.fitness_tracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ExerciseListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        reloadRV();
    }

    public void reloadRV() {
        recyclerView = findViewById(R.id.rv_exerciseList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DBHelper dbHelper = new DBHelper(ExerciseListActivity.this);
        List<Exercise> exercises = dbHelper.getExerciseList();

        mAdapter = new ExerciseViewAdapter(exercises, this, ExerciseListActivity.this);
        recyclerView.setAdapter(mAdapter);
    }
}