package com.example.fitness_tracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    ImageView avatar;
    TextView userName, description;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        avatar = getView().findViewById(R.id.avatarView);
        userName = getView().findViewById(R.id.userNameView);
        description = getView().findViewById(R.id.descriptionView);

        if (firebaseAuth.getCurrentUser() != null) {
            //userName.setText(firebaseAuth.getCurrentUser().getDisplayName());
            // userName.setText(firebaseAuth.getCurrentUser().getDisplayName());
            // Glide.with(this).load(firebaseAuth.getCurrentUser().getPhotoUrl().toString()).into(avatar);

            /// Set the avatar, username and description
        }
    }
}