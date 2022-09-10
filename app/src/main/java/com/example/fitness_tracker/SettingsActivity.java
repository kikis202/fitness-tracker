package com.example.fitness_tracker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    ImageView avatar;
    TextView userName;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        avatar = (ImageView) findViewById(R.id.avatarView);
        userName = (TextView) findViewById(R.id.userNameView);
       // description = (TextView) findViewById(R.id.descriptionView);

        if (firebaseAuth.getCurrentUser() != null) {
            userName.setText(firebaseAuth.getCurrentUser().getDisplayName());
            // userName.setText(firebaseAuth.getCurrentUser().getDisplayName());
            Glide.with(this).load(firebaseAuth.getCurrentUser().getPhotoUrl().toString()).into(avatar);
        }
    }
}
