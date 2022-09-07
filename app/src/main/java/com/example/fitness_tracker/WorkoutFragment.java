package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment {

    LinearLayout startEmptyWorkoutButton;

    public WorkoutFragment() {
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
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startEmptyWorkoutButton = (LinearLayout) getView().findViewById(R.id.linear_layout_button);
        startEmptyWorkoutButton.setOnClickListener(v -> startEmptyWorkout());
    }

    void startEmptyWorkout() {
        startActivity(new Intent(getActivity(), WorkoutDetailsActivity.class));
    }
}