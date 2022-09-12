package com.example.fitness_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
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
        WorkoutSet set = setList.get(position);
        int trackingID;

        if (trackingParameters.isReps()) {
            holder.exerciseTracking1.setText(String.valueOf(set.getReps()));
            if (trackingParameters.isWeight()) {
                // Reps + Weight
                holder.exerciseTracking2.setText(String.valueOf(set.getWeight()));
                trackingID = 1;
            } else {
                // Reps
                holder.exerciseTracking2.setVisibility(View.GONE);
                trackingID = 2;
            }
        } else {
            holder.exerciseTracking1.setText(String.valueOf(set.getTime()));
            if (trackingParameters.isDistance()) {
                // Time + Distance
                holder.exerciseTracking2.setText(String.valueOf(set.getDistance()));
                trackingID = 3;
            } else {
                // Time
                trackingID = 4;
                holder.exerciseTracking2.setVisibility(View.GONE);
            }
        }

        if (holder.exerciseTracking1.getText().toString().equals("0")) {
            holder.exerciseTracking1.setEnabled(true);
            holder.exerciseTracking2.setEnabled(true);
            holder.checkBox.setChecked(false);
        } else {
            holder.exerciseTracking1.setEnabled(false);
            holder.exerciseTracking2.setEnabled(false);
            holder.checkBox.setChecked(true);
        }

        holder.checkBox.setOnClickListener(v -> checkChanged(set.getId(), trackingID, position, holder));
    }

    public void checkChanged(int id, int trackingID, int pos, @NonNull MySetViewHolder holder) {
        if (holder.checkBox.isChecked()) {
            saveSet(id, trackingID, pos, holder);
            holder.exerciseTracking1.setEnabled(false);
            holder.exerciseTracking2.setEnabled(false);
            return;
        }
        holder.exerciseTracking1.setEnabled(true);
        holder.exerciseTracking2.setEnabled(true);
    }

    public void saveSet(int id, int trackingID, int pos, @NonNull MySetViewHolder holder) {
        String [] parameters;
        switch (trackingID) {
            case 1:
                parameters = new String[]{String.valueOf(holder.exerciseTracking1.getText()), String.valueOf(holder.exerciseTracking2.getText()), "0", "0"};
                break;
            case 2:
                parameters = new String[]{String.valueOf(holder.exerciseTracking1.getText()), "0", "0", "0"};
                break;
            case 3:
                parameters = new String[]{"0", "0", String.valueOf(holder.exerciseTracking1.getText()), String.valueOf(holder.exerciseTracking2.getText())};
                break;
            case 4:
                parameters = new String[]{"0", "0", "0", String.valueOf(holder.exerciseTracking1.getText())};
                break;
            default:
                parameters = new String[]{};
                throw new IllegalStateException("Unexpected value: " + trackingID);
        }
        int[] p;
        for (String pa:parameters) {
            if (!pa.matches("\\d+(?:\\.\\d+)?")) return;
        }

        if (parameters.length == 4) {
            p = new int[]{Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3])};
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.updateWorkoutSet(String.valueOf(id), p);
            int wExercise = setList.get(pos).getWorkoutExerciseID();
            setList = dbHelper.getWorkoutSets(wExercise);
        }

        System.out.println(Arrays.toString(parameters));
        System.out.println("yeah");
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
