package com.example.pet_booking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pet_booking_app.Adapters.PetAdapter;
import com.example.pet_booking_app.models.DBHelper;
import com.example.pet_booking_app.models.Pet;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "LoginPrefs";
    Button logoutButton;

    RecyclerView petsRecyclerView;
    PetAdapter petAdapter;
    TextInputEditText edittext_search;
    List<Pet> pets;
    List<Pet> filteredPets;

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

        DBHelper dbHelper = new DBHelper(HomeActivity.this);
        pets = dbHelper.getAllPets();


        petsRecyclerView = findViewById(R.id.petsRecyclerView);
        edittext_search = findViewById(R.id.edittext_search);

        petsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        filteredPets = new ArrayList<>(pets);
        petAdapter = new PetAdapter(filteredPets);
        petsRecyclerView.setAdapter(petAdapter);


        edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    void filter(String text) {
        Log.d("Filter", "Filtering with text: " + text);
        filteredPets.clear();
        for (Pet pet : pets) {
            if (pet.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredPets.add(pet);
            }
        }
        petAdapter.notifyDataSetChanged();
    }
}