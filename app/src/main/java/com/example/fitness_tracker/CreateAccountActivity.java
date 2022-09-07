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
import com.google.firebase.database.FirebaseDatabase;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CreateAccountActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;

//    private EditText emailEditText;
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

//        mAuth = FirebaseAuth.getInstance();

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
//        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_edit_text);

        loginButton = findViewById(R.id.login_text_view_button);
        createAccountButton = findViewById(R.id.submit_sign_up_button);

        progressBar = findViewById(R.id.progress_c);

        loginButton = (TextView) findViewById(R.id.login_text_view_button);
        loginButton.setOnClickListener(v -> startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class)));

        createAccountButton.setOnClickListener(v -> createUser());

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//           if(bundle.getString("some") !=null){
//               Toast.makeText(getApplicationContext(),
//                       "data: " + bundle.getString("some"),
//                       Toast.LENGTH_SHORT).show();
//           }
//
//        }

    }

    /*void createAccount() {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        boolean isValid = validateData(username, email, password, confirmPassword);
        if (!isValid) return;

        createAccountInFirebase(username, email, password);
    }*/


    boolean validateData(String username, String password, String confirmPassword){
        if (username.length() < 4) {
            usernameEditText.setError("Username must be at least 4 characters");
            usernameEditText.requestFocus();
            return false;
        }
        if (username.isEmpty()){
            usernameEditText.setError("Username is required!");
            usernameEditText.requestFocus();
            return false;
        }
//        if (email.isEmpty()){
//            emailEditText.setError("Email is required!");
//            emailEditText.requestFocus();
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailEditText.requestFocus();
//            return;
//        }
        if (password.isEmpty()){
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }
        if (password.length() < 7) {
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
//        String email = emailEditText.getText().toString().trim();
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

//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            User user = new User(username, email);
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                Toast.makeText(CreateAccountActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
//                                                progressBar.setVisibility(View.GONE);
//                                            }else {
//                                                Toast.makeText(CreateAccountActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                                                progressBar.setVisibility(View.GONE);
//                                            }
//                                        }
//                                    });
//
//                        }else{
//                            Toast.makeText(CreateAccountActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });
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



    /*void createAccountInFirebase(String username, String email, String password) {
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccountActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if (task.isSuccessful()) {
                            Utility.showToast(CreateAccountActivity.this, "Successfully created account");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        } else {
                            Utility.showToast(CreateAccountActivity.this, task.getException().getLocalizedMessage());
                        }
                    }
                });
    }*/

    /*void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            createAccountButton.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            createAccountButton.setVisibility(View.VISIBLE);
        }
    }*/
}