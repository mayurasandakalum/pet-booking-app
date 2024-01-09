package com.example.pet_booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet_booking_app.models.DBHelper;

public class PetDetailsView extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        String name = intent.getStringExtra("name");
        String birthday = intent.getStringExtra("birthday");
        String gender = intent.getStringExtra("gender");
        String breed = intent.getStringExtra("breed");
        String color = intent.getStringExtra("color");
        String otherDetails = intent.getStringExtra("otherDetails");

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView birthdayTextView = findViewById(R.id.birthdayTextView);
        TextView genderTextView = findViewById(R.id.genderTextView);
        TextView breedTextView = findViewById(R.id.breedTextView);
        TextView colorTextView = findViewById(R.id.colorTextView);
        TextView otherDetailsTextView = findViewById(R.id.otherDetailsTextView);

        Button requestBookBtn = findViewById(R.id.btn_toggle_book);

        requestBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming you have a PetDatabaseHelper instance db
                DBHelper db = new DBHelper(PetDetailsView.this);

                boolean success = db.updatePetIsBooked(id, "Yes");
                Log.d("updatedb", "onClick: " + success);
                if (success) {
                    Toast.makeText(PetDetailsView.this, "Pet booking status updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PetDetailsView.this, "Failed to update pet booking status", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                int caregiver_id = sharedPreferences.getInt("userId", 0);

                success = db.insertBooking(caregiver_id, id, name);
                if (success) {
                    Toast.makeText(PetDetailsView.this, "Booking made successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PetDetailsView.this, "Failed to make booking", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nameTextView.setText(name);
        birthdayTextView.setText(birthday);
        genderTextView.setText(gender);
        breedTextView.setText(breed);
        colorTextView.setText(color);
        otherDetailsTextView.setText(otherDetails);

    }
}