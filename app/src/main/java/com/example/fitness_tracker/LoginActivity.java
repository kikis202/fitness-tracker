package com.example.fitness_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
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

//        emailEditText = findViewById(R.id.email_edit_text);
        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);

        loginButton = findViewById(R.id.submit_login_button);

        createAccountButton = findViewById(R.id.create_account_text_view_button);

        progressBar = findViewById(R.id.progress_c);

        loginButton.setOnClickListener(v -> loginUser());
        createAccountButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class)));
    }

    void loginUser() {
//        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = validateData(username, password);
        if (!isValid) return;

        // Gets hashed password from db and verifies it
        String bcryptHashString = dbHelper.getPassword(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        if (result.verified) {
            Utility.showToast(LoginActivity.this, "Login successful");
            SaveSharedPreference.setUserName(LoginActivity.this, username);
            startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        } else {
            Utility.showToast(LoginActivity.this, "Incorrect username and/or password");
        }



//        loginAccountInFirebase(email, password);
    }

//    void loginAccountInFirebase(String email, String password) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        changeInProgress(true);
//        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                changeInProgress(false);
//                if (task.isSuccessful()) {
//                    // Successful login
//                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
//                        // Go to user account activity
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                        finish();
//                    } else {
//                        // Email not verified
//                        Utility.showToast(LoginActivity.this, "Email not verified, Please verify your Email");
//                    }
//                } else {
//                    // Failed login
//                    Utility.showToast(LoginActivity.this, task.getException().getLocalizedMessage());
//                }
//            }
//        });
//    }

    boolean validateData(String username, String password) {
        // Validate user input

//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailEditText.setError("Email is invalid");
//            return false;
//        }
//        if (password.length() < 7) {
//            passwordEditText.setError("Password must be at least 7 characters");
//            return false;
//        }

        if (username.length() < 4) {
            usernameEditText.setError("Username must be at least 4 characters");
            usernameEditText.requestFocus();
            return false;
        }
        if (username.isEmpty()) {
            usernameEditText.setError("Username is required!");
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

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }
    }

}