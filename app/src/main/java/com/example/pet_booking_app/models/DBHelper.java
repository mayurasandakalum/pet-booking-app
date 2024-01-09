package com.example.pet_booking_app.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pet_booking_app.Database.PetBookingDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper extends SQLiteOpenHelper {
    // db name
    public static final String dbName = "pet_booking_db";

    // table names
    public static final String customerTable = "customer";
    public static final String caregiverTable = "caregiver";
    public static final String petTable = "pet";
    public static final String caregiverServicesTable = "caregiver_services";
    public static final String bookingsTable = "bookings";

    // table create queries
    public static final String customerTableCreate = "CREATE TABLE " + customerTable + "(" + "id INTEGER PRIMARY KEY," + "full_name TEXT," + "address TEXT," + "birthday TEXT," + "gender TEXT," + "phone_number TEXT," + "email TEXT," + "password TEXT" + ")";

    public static final String careGiverTableCreate = "CREATE TABLE " + caregiverTable + "(" + "id INTEGER PRIMARY KEY," + "full_name TEXT," + "address TEXT," + "birthday TEXT," + "gender TEXT," + "phone_number TEXT," + "email TEXT," + "password TEXT" + ")";

    public static final String petTableCreate = "CREATE TABLE " + petTable + "(" + "id INTEGER PRIMARY KEY," + "owner_id INTEGER," + "name TEXT," + "type TEXT," + "birthday TEXT," + "gender TEXT," + "breed TEXT," + "color TEXT," + "other_details TEXT," + "is_booked TEXT" + ")";

    public static final String caregiverServicesTableCreate = "CREATE TABLE " + caregiverServicesTable + "(" + "id INTEGER PRIMARY KEY," + "caregiver_id INTEGER," + "service_types TEXT," + "service_locations TEXT" + ")";

    public static final String bookingsTableCreate = "CREATE TABLE " + bookingsTable + "(" + "id INTEGER PRIMARY KEY," + "caregiver_id INTEGER," + "pet_id INTEGER," + "pet_name TEXT" + ")";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(customerTableCreate);
        sqLiteDatabase.execSQL(careGiverTableCreate);
        sqLiteDatabase.execSQL(petTableCreate);
        sqLiteDatabase.execSQL(caregiverServicesTableCreate);
        sqLiteDatabase.execSQL(bookingsTableCreate);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + customerTable);
        sqLiteDatabase.execSQL("Drop table if exists " + caregiverTable);
        sqLiteDatabase.execSQL("Drop table if exists " + petTable);
        sqLiteDatabase.execSQL("Drop table if exists " + caregiverServicesTable);
        sqLiteDatabase.execSQL("Drop table if exists " + bookingsTable);

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

        contentValues.put("type", pet.getType());
        contentValues.put("owner_id", pet.getOwnerId());
        contentValues.put("name", pet.getName());
        contentValues.put("birthday", pet.getBirthday());
        contentValues.put("gender", pet.getGender());
        contentValues.put("breed", pet.getBreed());
        contentValues.put("color", pet.getColor());
        contentValues.put("other_details", pet.getOtherDetails());
        contentValues.put("is_booked", "No");

//        Log.d("dbpet", pet.getType() +
//                pet.getOwnerId() +
//                pet.getName() +
//                pet.getBirthday() +
//                pet.getGender() +
//                pet.getBreed() +
//                pet.getColor() +
//                pet.getOtherDetails());

        long result = db.insert(petTable, null, contentValues);

        // if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

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

    public List<Pet> getPetsByOwnerId(int ownerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pet> pets = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + petTable + " WHERE owner_id=?", new String[]{String.valueOf(ownerId)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int ownerIdIndex = cursor.getColumnIndex("owner_id");
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int genderIndex = cursor.getColumnIndex("gender");
                int breedIndex = cursor.getColumnIndex("breed");
                int colorIndex = cursor.getColumnIndex("color");
                int birthdayIndex = cursor.getColumnIndex("birthday");
                int otherDetailsIndex = cursor.getColumnIndex("other_details");
                int isBookedIndex = cursor.getColumnIndex("is_booked");

                if (idIndex >= 0 && ownerIdIndex >= 0 && nameIndex >= 0 && typeIndex >= 0 && genderIndex >= 0 && breedIndex >= 0 && colorIndex >= 0 && birthdayIndex >= 0 && otherDetailsIndex >= 0 && isBookedIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    int ownerIdDb = cursor.getInt(ownerIdIndex);
                    String name = cursor.getString(nameIndex);
                    String type = cursor.getString(typeIndex);
                    String gender = cursor.getString(genderIndex);
                    String breed = cursor.getString(breedIndex);
                    String color = cursor.getString(colorIndex);
                    String birthday = cursor.getString(birthdayIndex);
                    String otherDetails = cursor.getString(otherDetailsIndex);
                    String isBooked = cursor.getString(isBookedIndex);


                    Pet pet = new Pet(id, type, name, birthday, gender, breed, color, otherDetails, ownerIdDb, isBooked);

                    pets.add(pet);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pets;
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();

        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the SQL query
        String queryString = "SELECT * FROM " + petTable;

        // Execute the query
        Cursor cursor = db.rawQuery(queryString, null);

        // Iterate through the results and create Pet objects
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int ownerIdIndex = cursor.getColumnIndex("owner_id");
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int genderIndex = cursor.getColumnIndex("gender");
                int breedIndex = cursor.getColumnIndex("breed");
                int colorIndex = cursor.getColumnIndex("color");
                int birthdayIndex = cursor.getColumnIndex("birthday");
                int otherDetailsIndex = cursor.getColumnIndex("other_details");
                int isBookedIndex = cursor.getColumnIndex("is_booked");

                if (idIndex >= 0 && ownerIdIndex >= 0 && nameIndex >= 0 && typeIndex >= 0 && genderIndex >= 0 && breedIndex >= 0 && colorIndex >= 0 && birthdayIndex >= 0 && otherDetailsIndex >= 0 && isBookedIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    int ownerId = cursor.getInt(ownerIdIndex);
                    String name = cursor.getString(nameIndex);
                    String type = cursor.getString(typeIndex);
                    String gender = cursor.getString(genderIndex);
                    String breed = cursor.getString(breedIndex);
                    String color = cursor.getString(colorIndex);
                    String birthday = cursor.getString(birthdayIndex);
                    String otherDetails = cursor.getString(otherDetailsIndex);
                    String isBooked = cursor.getString(isBookedIndex);

                    Pet pet = new Pet(id, type, name, birthday, gender, breed, color, otherDetails, ownerId, isBooked);
                    pets.add(pet);
                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return pets;
    }

    public boolean updatePetIsBooked(int petId, String isBooked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_booked", isBooked);

        int rowsUpdated = db.update(petTable, contentValues, "id = ?", new String[]{String.valueOf(petId)});

        return rowsUpdated > 0;
    }

    // insert booking
    public boolean insertBooking(int caregiverId, int petId, String petName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("caregiver_id", caregiverId);
        values.put("pet_id", petId);
        values.put("pet_name", petName);

        long result = db.insert(bookingsTable, null, values);
        return result != -1;
    }

    // Get all bookings data for a caregiver
    public List<Booking> getBookingsForCaregiver(int caregiverId) {
        List<Booking> bookingsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + bookingsTable + " WHERE caregiver_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(caregiverId)});

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int petIdIndex = cursor.getColumnIndex("pet_id");
            int petNameIndex = cursor.getColumnIndex("pet_name");

            while (cursor.moveToNext()) {
                if (idIndex >= 0 && petIdIndex >= 0 && petNameIndex >= 0) {
                    int bookingId = cursor.getInt(idIndex);
                    int petId = cursor.getInt(petIdIndex);
                    String petName = cursor.getString(petNameIndex);

                    // Create a new Bookings object
                    Booking booking = new Booking(bookingId, caregiverId, petId, petName);

                    // Add the booking to the list
                    bookingsList.add(booking);
                }
            }
        }

        cursor.close();
        return bookingsList;
    }
}