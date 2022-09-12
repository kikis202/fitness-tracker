package com.example.fitness_tracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class DBHelper extends SQLiteOpenHelper {

    public final static String DBNAME = "FitPlus.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create table sql statements
        String sql_create_users = "CREATE TABLE users(username TEXT primary key, password TEXT)";
        String sql_create_user_profiles = "CREATE TABLE user_profiles(id INTEGER primary key autoincrement, user_id TEXT not null, description TEXT, profile_pic_name TEXT, FOREIGN KEY(user_id) REFERENCES users(username))";
        String sql_create_tracking_type = "CREATE TABLE tracking_types(id INTEGER primary key autoincrement, reps INTEGER, weight INTEGER, distance INTEGER, time INTEGER)";
        String sql_create_exercises = "CREATE TABLE exercises(id INTEGER primary key autoincrement, title TEXT not null, description TEXT, image_name TEXT, tracking_type_id INTEGER not null, FOREIGN KEY(tracking_type_id) REFERENCES tracking_types(id))";
        String sql_create_workouts = "CREATE TABLE workouts(id INTEGER primary key autoincrement, user_id TEXT not null, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY(user_id) REFERENCES users(username))";
        String sql_create_workout_exercises = "CREATE TABLE workout_exercises(id INTEGER primary key autoincrement, workout_id INTEGER not null, exercise_id INTEGER not null, FOREIGN KEY(workout_id) REFERENCES workouts(id),FOREIGN KEY(exercise_id) REFERENCES exercises(id))";
        String sql_create_workout_sets = "CREATE TABLE workout_sets(id INTEGER primary key autoincrement, workout_exercise_id INTEGER, reps INTEGER, weight INTEGER, distance INTEGER, time INTEGER, FOREIGN KEY(workout_exercise_id) REFERENCES workout_exercises(id))";

        // Create table sql statements executed
        sqLiteDatabase.execSQL(sql_create_users);
        sqLiteDatabase.execSQL(sql_create_user_profiles);
        sqLiteDatabase.execSQL(sql_create_tracking_type);
        sqLiteDatabase.execSQL(sql_create_exercises);
        sqLiteDatabase.execSQL(sql_create_workouts);
        sqLiteDatabase.execSQL(sql_create_workout_exercises);
        sqLiteDatabase.execSQL(sql_create_workout_sets);

        // Adds default Tracking parameters and Exercises
        seedDB(sqLiteDatabase);
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql_drop_workout_sets = "DROP TABLE IF EXISTS workout_sets";
        String sql_drop_workout_exercises = "DROP TABLE IF EXISTS workout_exercises";
        String sql_drop_workouts = "DROP TABLE IF EXISTS workouts";
        String sql_drop_exercises = "DROP TABLE IF EXISTS exercises";
        String sql_drop_tracking_type = "DROP TABLE IF EXISTS tracking_types";
        String sql_drop_user_profiles = "DROP TABLE IF EXISTS user_profiles";
        String sql_drop_users = "DROP TABLE IF EXISTS users";

        sqLiteDatabase.execSQL(sql_drop_workout_sets);
        sqLiteDatabase.execSQL(sql_drop_workout_exercises);
        sqLiteDatabase.execSQL(sql_drop_workouts);
        sqLiteDatabase.execSQL(sql_drop_exercises);
        sqLiteDatabase.execSQL(sql_drop_tracking_type);
        sqLiteDatabase.execSQL(sql_drop_user_profiles);
        sqLiteDatabase.execSQL(sql_drop_users);
    }

    public boolean insertUserProfile(String username, SQLiteDatabase myDB) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", username);
        contentValues.put("description", "");
        contentValues.put("profile_pic_name", "default");
        long result = myDB.insert("user_profiles", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public boolean insertUser(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1) return false;
        return insertUserProfile(username, myDB);
    }

    public boolean insertTrackingType(int reps, int weight, int distance, int time) {
        // Tracking type id's:
        // 1 - reps & weight
        // 2 - reps
        // 3 - distance & time
        // 4 - time
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("reps", reps);
        contentValues.put("weight", weight);
        contentValues.put("distance", distance);
        contentValues.put("time", time);
        long result = myDB.insert("tracking_types", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public boolean insertTrackingTypeRecursive(SQLiteDatabase myDB, int reps, int weight, int distance, int time) {
        // Tracking type id's:
        // 1 - reps & weight
        // 2 - reps
        // 3 - distance & time
        // 4 - time
        ContentValues contentValues = new ContentValues();
        contentValues.put("reps", reps);
        contentValues.put("weight", weight);
        contentValues.put("distance", distance);
        contentValues.put("time", time);
        long result = myDB.insert("tracking_types", null, contentValues);
        return result != -1;
    }

    public boolean seedTrackingTypes(SQLiteDatabase myDB) {
        int[] reps = {1, 1, 0, 0};
        int[] weight = {1, 0, 0, 0};
        int[] distance = {0, 0, 1, 0};
        int[] time = {0, 0, 1, 1};

        boolean successful_entry;
        for (int i = 0; i < reps.length; i++) {
            successful_entry = insertTrackingTypeRecursive(myDB, reps[i], weight[i], distance[i], time[i]);
            if (!successful_entry) return false;
        }

        return true;
    }

    public boolean insertExercise(String title, String description, String image_name, int tracking_type_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("image_name", image_name);
        contentValues.put("tracking_type_id", tracking_type_id);
        long result = myDB.insert("exercises", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public boolean insertExerciseRecursive(SQLiteDatabase myDB, String title, String description, String image_name, int tracking_type_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("image_name", image_name);
        contentValues.put("tracking_type_id", tracking_type_id);
        long result = myDB.insert("exercises", null, contentValues);
        return result != -1;
    }

    public long insertWorkout(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", username);
        long id = myDB.insert("workouts", null, contentValues);
        myDB.close();
        return id;
    }

    public long insertWorkoutExercise(int workoutID, int exerciseID) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("workout_id", workoutID);
        contentValues.put("exercise_id", exerciseID);
        long result = myDB.insert("workout_exercises", null, contentValues);
        myDB.close();
        return result;
    }

    public int insertWorkoutSet(int workout_exercise_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("workout_exercise_id", workout_exercise_id);
        long result = myDB.insert("workout_sets", null, contentValues);
        myDB.close();
        return (int) result;
    }

    public boolean seedExercises(SQLiteDatabase myDB) {
        // Hard coded entries
        String description_placeholder = "This is the default description, the real description will be set in the future";
        String[] titles = {
                "Back extension", "Bench press", "Bent over barbell row", "Bicycle crunches",
                "Dips", "Crunches", "Seated row (machine)", "Plank",
                "Hanging knee raises", "Lateral raises (dumbbell)", "Leg press", "Straight legged deadlifts"
        };
        String[] descriptions = {
                "Place your thighs on the pad. Bend your knees slightly and secure your feet, keeping them in line with your knees. Extend your arms toward the floor. Exhale and move up until your shoulders, spine, and hips are in line. Engage your core and gently slide your shoulders back. Inhale and bend down from your waist. Touch the floor. Complete the desired number of reps and sets.",
                "The bench press is a compound exercise that targets the muscles of the upper body. It involves lying on a bench and pressing weight upward using either a barbell",
                description_placeholder, description_placeholder, description_placeholder, description_placeholder, description_placeholder, description_placeholder,
                description_placeholder, description_placeholder, description_placeholder, description_placeholder
        };
        String[] image_names = {
                "exercise_back_extension", "exercise_bench_press", "exercise_bent_over_row_barbell", "exercise_bicycle_crunches",
                "exercise_dips", "exercise_crunches", "exercise_seated_row_machine", "exercise_plank",
                "exercise_hanging_knee_raise", "exercise_lateral_dumbbel_raises", "exercise_leg_press_machine", "exercise_straight_leg_deadlifts"
        };
        int[] tracking_type_id = {2, 1, 1, 2, 2, 2, 3, 4, 2, 1, 1, 1};

        // Seeding db
        boolean successful_entry;
        for (int i = 0; i < titles.length; i++) {
            successful_entry = insertExerciseRecursive(myDB, titles[i], descriptions[i], image_names[i], tracking_type_id[i]);
            if (!successful_entry) return false;
        }
        return true;
    }

    public boolean seedDB(SQLiteDatabase myDB) {
        if (!seedTrackingTypes(myDB)) return false;
        if (!seedExercises(myDB)) return false;
        return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT * FROM users WHERE username = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        myDB.close();
        return userExists;
    }

    @SuppressLint("Range")
    public void setDescription(String username, String description) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("description", description);
        myDB.update("user_profiles", cv, "user_id=?", new String[]{username});
    }

    @SuppressLint("Range")
    public void setUsername(String username, String user_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        myDB.update("user_profiles", cv, "user_id=?", new String[]{username});
        myDB.update("user", cv, "username=?", new String[]{username});
    }

    @SuppressLint("Range")
    public void setPic(String username, String url) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("profile_pic_name", url);
        myDB.update("user_profiles", cv, "user_id=?", new String[]{username});
    }

    @SuppressLint("Range")
    public void setPassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        cv.put("password", bcryptHashString);
        myDB.update("user", cv, "username=?", new String[]{username});
    }

    @SuppressLint("Range")
    public String getPic(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT profile_pic_name FROM user_profiles WHERE user_id = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            String text = cursor.getString(cursor.getColumnIndex("profile_pic_name"));
            cursor.close();
            myDB.close();
            return text;
        }
        myDB.close();
        return "";
    }

    @SuppressLint("Range")
    public String getDescription(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT description FROM user_profiles WHERE user_id = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            String text = cursor.getString(cursor.getColumnIndex("description"));
            cursor.close();
            myDB.close();
            return text;
        }
        myDB.close();
        return "";
    }

    @SuppressLint("Range")
    public String getPassword(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT password FROM users WHERE username = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            String hashedPassword = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();
            myDB.close();
            return hashedPassword;
        }
        myDB.close();
        return "";
    }

    public Exercise getExercise(int id) {
        String sql = "SELECT * FROM exercises WHERE id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String imageName = cursor.getString(3);
            int trackingTypeID = cursor.getInt(4);

            cursor.close();
            myDB.close();

            return new Exercise(id, trackingTypeID, title, description, imageName);
        }

        cursor.close();
        myDB.close();
        return new Exercise();
    }

    public List<Exercise> getExerciseList() {
        List<Exercise> returnList = new ArrayList<>();

        String sql = "SELECT * FROM exercises";
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int exerciseID = cursor.getInt(0);
                String exerciseTitle = cursor.getString(1);
                String exerciseDescription = cursor.getString(2);
                String exerciseImageName = cursor.getString(3);
                int exerciseTrackingType = cursor.getInt(4);
                Exercise newExercise = new Exercise(exerciseID, exerciseTrackingType, exerciseTitle, exerciseDescription, exerciseImageName);
                returnList.add(newExercise);
            } while (cursor.moveToNext());
        }

        cursor.close();
        myDB.close();
        return returnList;
    }

    public List<WorkoutExercise> getWorkoutExerciseList(int workoutID) {
        List<WorkoutExercise> returnList = new ArrayList<>();
        if (workoutID == 0) return returnList;

        String sql = "SELECT * FROM workout_exercises WHERE workout_id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery(sql, new String[]{String.valueOf(workoutID)});

        if (cursor.moveToFirst()) {
            do {
                // Get all workout exercises
                int id = cursor.getInt(0);
                int exerciseID = cursor.getInt(2);

                List<WorkoutSet> workoutSets = getWorkoutSets(id);
                Exercise exercise = getExercise(exerciseID);
                TrackingParameters trackingParameters = getExerciseTrackingParameters(exerciseID);

                WorkoutExercise newExercise = new WorkoutExercise(id, workoutID, exerciseID, workoutSets, exercise.getTitle(), exercise.getImage_name(), trackingParameters);
                returnList.add(newExercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        myDB.close();
        return returnList;
    }

    public TrackingParameters getTrackingParameters(int id) {
        String sql = "SELECT * FROM tracking_types WHERE id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();

        TrackingParameters trackingParameters = new TrackingParameters();

        Cursor cursor = myDB.rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            boolean reps = cursor.getInt(1) == 1;
            boolean weight = cursor.getInt(2) == 1;
            boolean distance = cursor.getInt(3) == 1;
            boolean time = cursor.getInt(4) == 1;
            trackingParameters.setReps(reps);
            trackingParameters.setWeight(weight);
            trackingParameters.setDistance(distance);
            trackingParameters.setTime(time);
        }
        cursor.close();
        myDB.close();
        return trackingParameters;
    }

    public TrackingParameters getExerciseTrackingParameters(int exerciseID) {
        String sql = "SELECT tracking_type_id FROM exercises WHERE id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery(sql, new String[]{String.valueOf(exerciseID)});

        if (cursor.moveToFirst()) {
            TrackingParameters trackingParameters = getTrackingParameters(cursor.getInt(0));
            cursor.close();
            myDB.close();
            return trackingParameters;
        }

        cursor.close();
        myDB.close();
        return new TrackingParameters();
    }

    public List<WorkoutSet> getWorkoutSets(int workoutExerciseID) {
        List<WorkoutSet> workoutSets = new ArrayList<>();

        String sql = "SELECT * FROM workout_sets WHERE workout_exercise_id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery(sql, new String[]{String.valueOf(workoutExerciseID)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int reps = cursor.getInt(2);
                int weight = cursor.getInt(3);
                int distance = cursor.getInt(4);
                int time = cursor.getInt(5);
                WorkoutSet workoutSet = new WorkoutSet(id, workoutExerciseID, reps, weight, distance, time);
                workoutSets.add(workoutSet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        myDB.close();
        return workoutSets;
    }

    public void updateWorkoutSet(String id, @NonNull int[] param) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        if (param.length > 0) {
            ContentValues values = new ContentValues();
            values.put("reps", param[0]);
            values.put("weight", param[1]);
            values.put("distance", param[2]);
            values.put("time", param[3]);

            myDB.update("workout_sets", values, "id = ?", new String[]{id});
        }
        myDB.close();
    }

    public List<Workout> getUserWorkouts(String username) {
        List<Workout> workouts = new ArrayList<>();

        String sql = "SELECT * FROM workouts WHERE user_id = ?";
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                String timestamp = cursor.getString(2);
                int id = cursor.getInt(0);

                sql = "SELECT * FROM workout_exercises WHERE workout_id = ?";
                Cursor c = myDB.rawQuery(sql, new String[]{String.valueOf(id)});
                int exercise_count = c.getCount();

                int set_count = 0;

                if (c.moveToFirst()) {
                    do {
                        int w_ex_id = c.getInt(0);
                        sql = "SELECT * FROM workout_sets WHERE workout_exercise_id = ?";
                        Cursor c2 = myDB.rawQuery(sql, new String[]{String.valueOf(w_ex_id)});

                        set_count += c2.getCount();
                        c2.close();
                    } while (c.moveToNext());
                }
                c.close();

                Workout workout = new Workout(username, timestamp, set_count, exercise_count);
                workouts.add(workout);
            } while (cursor.moveToNext());
        }

        myDB.close();
        cursor.close();
        return workouts;
    }
}
