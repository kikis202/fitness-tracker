package com.example.fitness_tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String USERNAME = "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUsername(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USERNAME, userName);
        editor.commit();
    }

    public static String getUsername(Context ctx) {
        return getSharedPreferences(ctx).getString(USERNAME, "");
    }
}