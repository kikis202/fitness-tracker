package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button createAccountButton;
    private TextView loginButton;
    private ProgressBar progressBar;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        dbHelper = new DBHelper(this);
        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_edit_text);
        loginButton = findViewById(R.id.login_text_view_button);
        createAccountButton = findViewById(R.id.submit_sign_up_button);
        progressBar = findViewById(R.id.progress_c);
        loginButton = (TextView) findViewById(R.id.login_text_view_button);

        loginButton.setOnClickListener(v -> startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class)));
        createAccountButton.setOnClickListener(v -> createUser());
    }

    boolean validateData(String username, String password, String confirmPassword) {
        if (username.length() <= 4) {
            usernameEditText.setError("Username must be at least 4 characters");
            usernameEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }
        if (password.length() <= 7) {
            passwordEditText.setError("Password must be at least 7 characters");
            passwordEditText.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords doesn't match");
            confirmPasswordEditText.setText("");
            return false;
        }
        if (dbHelper.checkUsername(username)) {
            Utility.showToast(CreateAccountActivity.this, "User already exists");
            return false;
        }
        return true;
    }

    private void createUser() {
        // Validate user input
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Checks if user inputted data are valid
        Boolean isValid = validateData(username, password, confirmPassword);
        if (!isValid) return;

        // Hashes the password
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        if (result.verified) {
            // Tries to save user
            Boolean insert = dbHelper.insertUser(username, bcryptHashString);
            if (insert) {
                // Successful account creation
                Utility.showToast(CreateAccountActivity.this, "Registered successfully");
                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            } else {
                // Unsuccessful account creation
                Utility.showToast(CreateAccountActivity.this, "Registration failed");
            }
        } else {
            Utility.showToast(CreateAccountActivity.this, "Hashing failed");
        }
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            return;
        }
        progressBar.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }
}