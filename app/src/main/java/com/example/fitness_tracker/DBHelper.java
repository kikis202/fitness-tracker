package com.example.fitness_tracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public final static String DBNAME = "FitPlus.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE users(username TEXT primary key, password TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS users";
        sqLiteDatabase.execSQL(sql);
    }

    public Boolean insertUser(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT * FROM users WHERE username = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkPassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username, password});
        return cursor.getCount() > 0;
    }

    @SuppressLint("Range")
    public String getPassword(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "SELECT password FROM users WHERE username = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("password"));
        }
        return "";
    }
}
