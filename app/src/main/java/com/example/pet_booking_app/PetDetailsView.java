package com.example.pet_booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PetDetailsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details_view);

        Intent intent = getIntent();
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

        nameTextView.setText(name);
        birthdayTextView.setText(birthday);
        genderTextView.setText(gender);
        breedTextView.setText(breed);
        colorTextView.setText(color);
        otherDetailsTextView.setText(otherDetails);

    }
}