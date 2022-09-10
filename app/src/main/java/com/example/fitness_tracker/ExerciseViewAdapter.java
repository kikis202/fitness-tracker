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

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ExerciseViewAdapter extends RecyclerView.Adapter<ExerciseViewAdapter.MyViewHolder> {
    List<Exercise> exercises;
    Context context;

    public ExerciseViewAdapter(List<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_exercise, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_exTitle.setText(exercises.get(position).getTitle());

        String uri = "@drawable/" + exercises.get(position).getImage_name();
        int imgResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imgResource);

        holder.iv_exImage.setImageDrawable(res);

        holder.button_addExercise.setOnClickListener(v -> addExercise(exercises.get(position).getId()));
    }

    public void addExercise(int exerciseID) {
        DBHelper dbHelper = new DBHelper(context);
        int wExId = (int) dbHelper.insertWorkoutExercise(SaveSharedPreference.getActiveWorkoutID(context), exerciseID);
        dbHelper.insertWorkoutSet(wExId);

        context.startActivity(new Intent(context, WorkoutDetailsActivity.class));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_exImage;
        TextView tv_exTitle;
        LinearLayout button_addExercise;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_exImage = itemView.findViewById(R.id.iv_exerciseImage);
            tv_exTitle = itemView.findViewById(R.id.tv_exerciseTitle);
            button_addExercise = itemView.findViewById(R.id.oneLineExerciseLayout);
        }
    }
}
