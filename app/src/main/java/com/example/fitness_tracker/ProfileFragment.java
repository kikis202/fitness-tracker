package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    ImageView avatar;
    TextView userName, description;
    private Button logout;



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



        logout = (Button) getView().findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserName(getActivity(), "");
                Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
                intent.putExtra("some", "User Logged Out");
                startActivity(intent);


            }
        });

    }
}