package com.example.fitness_tracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private LinearLayout noWorkouts;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Workout> workouts;

    DBHelper dbHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noWorkouts = getView().findViewById(R.id.no_workout_section);
        recyclerView = getView().findViewById(R.id.rv_workoutHistory);

        dbHelper = new DBHelper(getActivity());
        workouts = dbHelper.getUserWorkouts(SaveSharedPreference.getUserName(getContext()));

        Collections.reverse(workouts);

        if (workouts.size() > 0) {
            noWorkouts.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            noWorkouts.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();



        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);




            mAdapter = new WorkoutHistoryAdapter(workouts, getContext());
            recyclerView.setAdapter(mAdapter);
        }
    }
}