package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class SettingsActivity extends AppCompatActivity {
    ImageView avatar;
    TextView userName;
    ImageView backToProfile;
    AppCompatButton editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_settings);
        avatar = (ImageView) findViewById(R.id.avatarView);
        userName = (TextView) findViewById(R.id.userNameView);
        backToProfile = (ImageView) findViewById(R.id.back_to_profile);
        editProfile = (AppCompatButton) findViewById(R.id.edit_profile);
        backToProfile.setOnClickListener(v -> startActivity(new Intent(SettingsActivity.this, MainActivity.class)));
        editProfile.setOnClickListener(v -> startActivity(new Intent(SettingsActivity.this, EditActivity.class)));
        userName.setText(SaveSharedPreference.getUserName(this));
        Glide.with(this).load(dbHelper.getPic(SaveSharedPreference.getUserName(this))).into(avatar);
    }
}
