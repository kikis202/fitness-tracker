package com.example.fitness_tracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutDetailsAdapter extends RecyclerView.Adapter<WorkoutDetailsAdapter.MyExerciseViewHolder> {
    RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    List<WorkoutExercise> workoutExerciseList;
    Context context;


    public WorkoutDetailsAdapter(List<WorkoutExercise> workoutExerciseList, Context context) {
        this.workoutExerciseList = workoutExerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_workout_exercise, parent, false);
        MyExerciseViewHolder holder = new MyExerciseViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsAdapter.MyExerciseViewHolder holder, int position) {
        WorkoutExercise workoutExercise = workoutExerciseList.get(position);
        // Exercise title
        holder.tv_exerciseTitle.setText(workoutExercise.getExerciseTitle());

        // Exercise image
        String uri = "@drawable/" + workoutExercise.getExerciseImageName();
        int imgResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imgResource);
        holder.iv_exerciseImage.setImageDrawable(res);

        // Tracking type
        TrackingParameters tracking = workoutExercise.getTrackingParameters();
        if (tracking.isReps()) {
            holder.tv_trackingType1.setText("Reps");
            if (tracking.isWeight()) {
                // Reps + Weight
                holder.tv_trackingType2.setText("Weight");
            } else {
                // Reps
                holder.tv_trackingType2.setVisibility(View.GONE);
            }
        } else {
            holder.tv_trackingType1.setText("Time");
            if (tracking.isDistance()) {
                // Time + Distance
                holder.tv_trackingType2.setText("Distance");
            } else {
                // Time
                holder.tv_trackingType2.setVisibility(View.GONE);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setInitialPrefetchItemCount(workoutExercise.getWorkoutSets().size());
        WorkoutSetAdapter workoutSetAdapter = new WorkoutSetAdapter(workoutExercise.getWorkoutSets(), workoutExercise.getTrackingParameters(), context);

        holder.rv_workoutSets.setLayoutManager(layoutManager);
        holder.rv_workoutSets.setAdapter(workoutSetAdapter);
        holder.rv_workoutSets.setRecycledViewPool(viewPool);

        holder.button_addSet.setOnClickListener(v -> addSet(workoutExercise.getId(), workoutExercise.getWorkoutID(), position, holder));
    }

    public void addSet(int workoutExerciseID, int workoutID, int position, @NonNull WorkoutDetailsAdapter.MyExerciseViewHolder holder) {
        DBHelper dbHelper = new DBHelper(context);
        int pos = dbHelper.insertWorkoutSet(workoutExerciseID);

        workoutExerciseList = dbHelper.getWorkoutExerciseList(workoutID);
        WorkoutExercise workoutExercise = workoutExerciseList.get(position);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setInitialPrefetchItemCount(workoutExercise.getWorkoutSets().size());
        WorkoutSetAdapter workoutSetAdapter = new WorkoutSetAdapter(workoutExercise.getWorkoutSets(), workoutExercise.getTrackingParameters(), context);

        holder.rv_workoutSets.setLayoutManager(layoutManager);
        holder.rv_workoutSets.setAdapter(workoutSetAdapter);
        holder.rv_workoutSets.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return workoutExerciseList.size();
    }

    public void deleteExercise(int workoutExerciseID) {

    }

    public class MyExerciseViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_workoutSets;
        ImageView iv_exerciseImage, iv_exerciseOptions;
        TextView tv_exerciseTitle, tv_trackingType1, tv_trackingType2;
        LinearLayout button_addSet;

        public MyExerciseViewHolder(@NonNull View v) {
            super(v);
            rv_workoutSets = v.findViewById(R.id.rv_workoutSet);

            iv_exerciseImage = v.findViewById(R.id.iv_workoutExerciseImage);
            iv_exerciseOptions = v.findViewById(R.id.iv_workoutExerciseOptions);

            tv_exerciseTitle = v.findViewById(R.id.tv_workoutExerciseTitle);
            tv_trackingType1 = v.findViewById(R.id.tv_trackingType1);
            tv_trackingType2 = v.findViewById(R.id.tv_trackingType2);

            button_addSet = v.findViewById(R.id.ll_button_addSet);
        }
    }
}
