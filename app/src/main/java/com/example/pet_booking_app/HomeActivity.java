package com.example.pet_booking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pet_booking_app.Adapters.PetAdapter;
import com.example.pet_booking_app.models.Pet;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "LoginPrefs";
    Button logoutButton;

    RecyclerView petsRecyclerView;
    PetAdapter petAdapter;
    List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutButton = findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Redirect to login screen */
                Intent intent = new Intent(HomeActivity.this, CaregiverBookings.class);
                startActivity(intent);
                finish();
            }
        });

        pets = new ArrayList<Pet>();

        pets.add(new Pet("Dog", "Buddy", "2015-06-08", "Male", "Golden Retriever", "Golden", "Friendly and active"));
        pets.add(new Pet("Cat", "Whiskers", "2017-04-15", "Female", "Siamese", "Cream", "Quiet and independent"));
        pets.add(new Pet("Dog", "Max", "2018-01-20", "Male", "Bulldog", "White", "Loyal and protective"));
        pets.add(new Pet("Cat", "Bella", "2016-09-30", "Female", "Persian", "Grey", "Affectionate and calm"));
        pets.add(new Pet("Dog", "Charlie", "2019-12-05", "Male", "Labrador Retriever", "Black", "Outgoing and even tempered"));


        petsRecyclerView = findViewById(R.id.petsRecyclerView);
        petsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        petAdapter = new PetAdapter(pets);
        petsRecyclerView.setAdapter(petAdapter);
    }
}