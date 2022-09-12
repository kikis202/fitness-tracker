package com.example.fitness_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutHistoryAdapter extends RecyclerView.Adapter<WorkoutHistoryAdapter.MyHistoryHolder> {
    List<Workout> workouts;
    Context context;


    public WorkoutHistoryAdapter(List<Workout> workouts, Context context) {
        this.workouts = workouts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_workout, parent, false);

        return new MyHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHistoryHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.tv_date.setText(workout.getDate());
        holder.tv_exercises.setText(String.valueOf(workout.getExercises()));
        holder.tv_sets.setText(String.valueOf(workout.getSets()));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public class MyHistoryHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_exercises, tv_sets;
        public MyHistoryHolder(@NonNull View v) {
            super(v);
            tv_date = v.findViewById(R.id.tv_workoutDate);
            tv_exercises = v.findViewById(R.id.tv_workoutExercises);
            tv_sets = v.findViewById(R.id.tv_workoutSets);
        }
    }
}
