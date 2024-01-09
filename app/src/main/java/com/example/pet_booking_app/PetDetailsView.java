package com.example.pet_booking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

        Log.d("PetDetailsView", "Name: " + name + ", Birthday: " + birthday + ", Gender: " + gender + ", Breed: " + breed + ", Color: " + color + ", Other details: " + otherDetails);
    }
}