package com.example.pet_booking_app.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pet_booking_app.Database.PetBookingDB;

import java.util.HashMap;
import java.util.Map;


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

    // signup customer
    public boolean signupCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("full_name", customer.getFullName());
        contentValues.put("address", customer.getAddress());
        contentValues.put("birthday", customer.getBirthday());
        contentValues.put("gender", customer.getGender());
        contentValues.put("phone_number", customer.getPhoneNumber());
        contentValues.put("email", customer.getEmail());
        contentValues.put("password", customer.getPassword());

        long result = db.insert(customerTable, null, contentValues);

        // if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // signup caregiver
    public boolean signupCaregiver(Caregiver caregiver) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", caregiver.getId());
        contentValues.put("full_name", caregiver.getFullName());
        contentValues.put("address", caregiver.getAddress());
        contentValues.put("birthday", caregiver.getBirthday());
        contentValues.put("gender", caregiver.getGender());
        contentValues.put("phone_number", caregiver.getPhoneNumber());
        contentValues.put("email", caregiver.getEmail());
        contentValues.put("password", caregiver.getPassword());

        long result = db.insert(caregiverTable, null, contentValues);

        // if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // register pet
    public boolean registerPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", pet.getId());
        contentValues.put("type", pet.getType());
        contentValues.put("name", pet.getName());
        contentValues.put("birthday", pet.getBirthday());
        contentValues.put("gender", pet.getGender());
        contentValues.put("breed", pet.getBreed());
        contentValues.put("color", pet.getColor());
        contentValues.put("other_details", pet.getOtherDetails());

        long result = db.insert(petTable, null, contentValues);

        // if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public Object login(String username, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {"email", "password"};
//
//        // check in customer table
//        Cursor customerCursor = db.query(customerTable, columns, "email=? AND password=?", new String[]{username, password}, null, null, null);
//        if (customerCursor != null && customerCursor.moveToFirst()) {
//            return "customer";
//        }
//
//        // check in caregiver table
//        Cursor caregiverCursor = db.query(caregiverTable, columns, "email=? AND password=?", new String[]{username, password}, null, null, null);
//        if (caregiverCursor != null && caregiverCursor.moveToFirst()) {
//            return "caregiver";
//        }
//
//        // if no match found in either table
//        return false;
//    }

    public Map<String, Object> login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Map<String, Object> result = new HashMap<>();

        // check in customer table
        Cursor customerCursor = db.rawQuery("SELECT * FROM " + customerTable + " WHERE email=? AND password=?", new String[]{username, password});
        if (customerCursor != null && customerCursor.moveToFirst()) {
            int idIndex = customerCursor.getColumnIndex("id");
            if (idIndex >= 0) {
                result.put("userType", "customer");
                result.put("id", customerCursor.getInt(idIndex));
                return result;
            }
        }

        // check in caregiver table
        Cursor caregiverCursor = db.rawQuery("SELECT * FROM " + caregiverTable + " WHERE email=? AND password=?", new String[]{username, password});
        if (caregiverCursor != null && caregiverCursor.moveToFirst()) {
            int idIndex = caregiverCursor.getColumnIndex("id");
            if (idIndex >= 0) {
                result.put("userType", "caregiver");
                result.put("id", caregiverCursor.getInt(idIndex));
                return result;
            }
        }

        // if no match found in either table
        return null;
    }

//    public Boolean insertData(Users users) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("username", users.getUsername());
//        values.put("password", users.getPassword());
//        values.put("name", users.getName());
//        values.put("address", users.getAddress());
//        values.put("gender", users.getGender());
//        values.put("roleId", users.getRoleId());
//        long result = db.insert("User", null, values);
//        if (result == -1) return false;
//        else return true;
//    }

//    public Boolean checkUsername(String username) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{username});
//        if (cursor.getCount() > 0) {
//            return true;
//        } else return false;
//    }
//
//    public Boolean checkUsernamePasswordForUser(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from User where username=? and password=? and roleId=2", new String[]{username, password});
//        if (cursor.getCount() > 0) {
//            return true;
//        } else return false;
//    }

//    public Boolean checkUsernamePasswordForAdmin(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from User where username=? and password=? and roleId=1", new String[]{username, password});
//        if (cursor.getCount() > 0) {
//            return true;
//        } else return false;
//    }


}
