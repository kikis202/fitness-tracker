package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {
    ImageView backToSettings;
    ImageView avatar;
    EditText profilePicture;
    EditText userName;
    EditText password;
    EditText description;
    AppCompatButton editSave;
    String userNameShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_edit);
        userNameShared = SaveSharedPreference.getUserName(this);
        profilePicture = (EditText) findViewById(R.id.edit_profile_pic);
        avatar = (ImageView) findViewById(R.id.avatarView);
        userName = (EditText) findViewById(R.id.edit_profile_username);
        password = (EditText) findViewById(R.id.edit_profile_password);
        backToSettings = (ImageView) findViewById(R.id.back_to_settings);
        description = (EditText) findViewById(R.id.edit_profile_description);
        editSave = (AppCompatButton) findViewById(R.id.edit_save);
        DBHelper dbHelper = new DBHelper(this);
        Glide.with(this).load(dbHelper.getPic(SaveSharedPreference.getUserName(this))).into(avatar);
        backToSettings.setOnClickListener(v -> startActivity(new Intent(EditActivity.this, SettingsActivity.class)));
        editSave.setOnClickListener(v -> saveData());
    }
    public void saveData(){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.setDescription(userNameShared,description.getText().toString());
        dbHelper.setPassword(userNameShared,password.getText().toString());
        dbHelper.setUsername(userNameShared, userName.getText().toString());
        dbHelper.setPic(userNameShared,profilePicture.getText().toString());
        SaveSharedPreference.setUserName(this,userName.getText().toString());
        startActivity(new Intent(EditActivity.this, SettingsActivity.class));
        Log.e("########",dbHelper.getDescription(userNameShared));
    }
}
