package com.example.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ExerciseListActivity extends AppCompatActivity {

    int[] newArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);



        newArray = new int[]{

                R.id.backExtension,R.id.benchPress,R.id.bentOverRowBarbell,R.id.bicycleCrunches, R.id.concentrationCurls, R.id.crunches,
                R.id.dips, R.id.dumbbellCurl, R.id.dumbbellFly, R.id.dumbbellBenchPress, R.id.hangingKneeRaise, R.id.inclineBenchPress,
                R.id.lateralDumbbellRaises, R.id.legCurl, R.id.legExtension, R.id.legPress, R.id.legRaises, R.id.overheadTricepsPress,
                R.id.plank, R.id.preacherCurl, R.id.pullDownStock, R.id.pullUp, R.id.pushUp, R.id.rearDeltRaise, R.id.seatedRowMachine,
                R.id.shoulderPress, R.id.sidePlank, R.id.squats, R.id.straightBarCurl, R.id.straightLegDeadlifts, R.id.sitUps, R.id.tricepKickbacks,
                R.id.tricepPress, R.id.tricepPullovers, R.id.tricepPulloversWithDumbbell, R.id.uprightRows, R.id.walkingLungesDumbbells,
                R.id.weightedCalfRaises,

        };

    }
    public void Imagebuttonclicked(View view) {

        for (int i = 0; i< newArray.length; i++){

            if(view.getId() == newArray[i]){
            int value = i+1;
                Log.i("FIRST", String.valueOf(value));
                Intent intent = new Intent(ExerciseListActivity.this, WorkoutDetailsActivity.class);

                startActivity(intent);


            }
        }
    }
}