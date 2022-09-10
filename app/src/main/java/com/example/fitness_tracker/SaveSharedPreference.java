package com.example.fitness_tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String USERNAME = "username";
    static final String PREF_ACTIVE_WORKOUT_ID= "workout_id";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USERNAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(USERNAME, "");
    }

    public static void setActiveWorkoutID(Context ctx, int workoutID)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_ACTIVE_WORKOUT_ID, workoutID);
        editor.commit();
    }

    public static int getActiveWorkoutID(Context ctx)
    {
        return getSharedPreferences(ctx).getInt(PREF_ACTIVE_WORKOUT_ID, 0);
    }
}
