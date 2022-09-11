package com.example.fitness_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutSetAdapter extends RecyclerView.Adapter<WorkoutSetAdapter.MySetViewHolder> {
    List<WorkoutSet> setList;
    Context context;
    TrackingParameters trackingParameters;

    public WorkoutSetAdapter(List<WorkoutSet> setList, TrackingParameters trackingParameters, Context context) {
        this.setList = setList;
        this.trackingParameters = trackingParameters;
        this.context = context;
    }

    @NonNull
    @Override
    public MySetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_workout_set, parent, false);
        MySetViewHolder holder = new MySetViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MySetViewHolder holder, int position) {
        if (trackingParameters.isReps()) {
            holder.exerciseTracking1.setText("0");
            if (trackingParameters.isWeight()) {
                // Reps + Weight
                holder.exerciseTracking2.setText("0");
            } else {
                // Reps
                holder.exerciseTracking2.setVisibility(View.GONE);
            }
        } else {
            holder.exerciseTracking1.setText("0");
            if (trackingParameters.isDistance()) {
                // Time + Distance
                holder.exerciseTracking2.setText("0");
            } else {
                // Time
                holder.exerciseTracking2.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return setList.size();
    }

    public class MySetViewHolder extends RecyclerView.ViewHolder {
        EditText exerciseTracking1, exerciseTracking2;
        CheckBox checkBox;

        public MySetViewHolder(@NonNull View v) {
            super(v);
            exerciseTracking1 = v.findViewById(R.id.edit_exerciseTracking1);
            exerciseTracking2 = v.findViewById(R.id.edit_exerciseTracking2);
            checkBox = v.findViewById(R.id.checkBox);
        }
    }
}
