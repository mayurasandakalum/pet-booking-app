package com.example.pet_booking_app.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pet_booking_app.Database.PetBookingDB;


public class DBHelper extends SQLiteOpenHelper {
    // db name
    public static final String dbName = "pet_booking_db";

    // table names
    public static final String customerTable = "customer";
    public static final String caregiverTable = "caregiver";
    public static final String petTable = "pet";

    // table create queries
    public static final String customerTableCreate = "CREATE TABLE " + customerTable + "(" +
            "id INTEGER PRIMARY KEY," +
            "full_name TEXT," +
            "address TEXT," +
            "birthday TEXT," +
            "gender TEXT," +
            "phone_number TEXT," +
            "email TEXT," +
            "password TEXT" +
            ")";

    public static final String careGiverTableCreate = "CREATE TABLE " + caregiverTable + "(" +
            "id INTEGER PRIMARY KEY," +
            "full_name TEXT," +
            "address TEXT," +
            "birthday TEXT," +
            "gender TEXT," +
            "phone_number TEXT," +
            "email TEXT," +
            "password TEXT" +
            ")";

    public static final String petTableCreate = "CREATE TABLE " + petTable + "(" +
            "id INTEGER PRIMARY KEY," +
            "owner_id INTEGER," +
            "name TEXT," +
            "birthday TEXT," +
            "gender TEXT," +
            "pet_type TEXT," +
            "breed TEXT," +
            "color TEXT," +
            "other_details TEXT" +
            ")";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(customerTableCreate);
        sqLiteDatabase.execSQL(careGiverTableCreate);
        sqLiteDatabase.execSQL(petTableCreate);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + customerTable);
        sqLiteDatabase.execSQL("Drop table if exists " + caregiverTable);
        sqLiteDatabase.execSQL("Drop table if exists " + petTable);

        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", users.getUsername());
        values.put("password", users.getPassword());
        values.put("name", users.getName());
        values.put("address", users.getAddress());
        values.put("gender", users.getGender());
        values.put("roleId", users.getRoleId());
        long result = db.insert("User", null, values);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

    public Boolean checkUsernamePasswordForUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username=? and password=? and roleId=2", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

    public Boolean checkUsernamePasswordForAdmin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username=? and password=? and roleId=1", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }


}
