package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class ProfileFragment extends Fragment {
    ImageView avatar;
    TextView userName, description;
    private Button logout;
    private ImageView settings;

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
        avatar = getView().findViewById(R.id.avatarView);
        userName = getView().findViewById(R.id.userNameView);
        description = getView().findViewById(R.id.descriptionView);
        logout = (Button) getView().findViewById(R.id.signOut);
        settings = (ImageView) getView().findViewById(R.id.profile_settings_button);
        DBHelper dbHelper = new DBHelper(this.getContext());
        description.setText(dbHelper.getDescription(SaveSharedPreference.getUserName(this.getContext())));
        userName.setText(SaveSharedPreference.getUserName(this.getContext()));
        Glide.with(this).load(dbHelper.getPic(SaveSharedPreference.getUserName(this.getContext()))).into(avatar);
        settings.setOnClickListener(v -> startActivity(new Intent(ProfileFragment.this.getContext(), SettingsActivity.class)));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserName(ProfileFragment.super.getContext(),"");
                Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
                intent.putExtra("some", "User Logged Out");
                startActivity(intent);

            }
        });
    }
}