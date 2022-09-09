package com.example.fitness_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutDetailsActivity extends AppCompatActivity {
    LinearLayout addExerciseButton, deleteWorkoutButton;
    TextView saveWorkoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        TextView firstView = (TextView) findViewById(R.id.firstView);

        /*Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("first");
        firstView.setText(data);*/

        addExerciseButton = findViewById(R.id.add_exercise_button);
        deleteWorkoutButton = findViewById(R.id.delete_workout_button);
        saveWorkoutButton = findViewById(R.id.save_workout_button);

        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExerciseList();
            }
        });







    }
    public void openExerciseList(){
        Intent intent = new Intent(this, ExerciseListActivity.class);
        startActivity(intent);
    }

    void testFunc(){
        System.out.println("started");
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://fitness-tracker-45b64-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference exerciseRef = database.getReference("exercises");
        System.out.println(1);
        String description = "The bench press is a compound exercise that targets the muscles of the upper body. It involves lying on a bench and pressing weight upward using either a barbell";
        TrackingParameters trackingParameters = new TrackingParameters(true, true, false, false);
        Exercise exercise = new Exercise("Bench press", description, "exercise_bench_press", trackingParameters);
        System.out.println(2);
        exerciseRef.push().setValue(exercise).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Utility.showToast(WorkoutDetailsActivity.this, "Successful");
                    System.out.println("yay");
                } else {
                    Utility.showToast(WorkoutDetailsActivity.this, "Failed");
                    System.out.println("nay");
                }
            }
        });
        System.out.println(3);
    }


}