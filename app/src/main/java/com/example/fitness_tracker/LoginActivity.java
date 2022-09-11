package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView createAccountButton;
    private ProgressBar progressBar;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.submit_login_button);
        createAccountButton = findViewById(R.id.create_account_text_view_button);
        progressBar = findViewById(R.id.progress_c);

        loginButton.setOnClickListener(v -> loginUser());
        createAccountButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class)));
    }

    void loginUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isValid = validateData(username, password);

        if (!isValid) return;
        // Gets hashed password from db and verifies it
        String bcryptHashString = dbHelper.getPassword(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        if (result.verified) {
            SaveSharedPreference.setUserName(LoginActivity.this,username);
            Log.i("username ",SaveSharedPreference.getUserName(LoginActivity.this));
            Utility.showToast(LoginActivity.this, "Login successful");
            startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        } else {
            Utility.showToast(LoginActivity.this, "Incorrect username and/or password");
        }
    }

    boolean validateData(String username, String password) {
        // Validate user input
        if (username.length() < 4) {
            usernameEditText.setError("Username must be at least 4 characters");
            usernameEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }
        if (password.length() < 7) {
            passwordEditText.setError("Password must be at least 7 characters");
            passwordEditText.requestFocus();
            return false;
        }
        return true;
    }
}