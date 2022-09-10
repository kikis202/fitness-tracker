package com.example.fitness_tracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fitness_tracker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.e("username ", SaveSharedPreference.getUsername(MainActivity.this));

        BottomNavigationView mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.getMenu().findItem(R.id.home_nav).setChecked(false);
        mBottomNavigationView.getMenu().findItem(R.id.profile_nav).setChecked(false);
        mBottomNavigationView.getMenu().findItem(R.id.workout_nav).setChecked(true);

        replaceFragment(new WorkoutFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_nav:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.workout_nav:
                    replaceFragment(new WorkoutFragment());
                    break;
                case R.id.profile_nav:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}