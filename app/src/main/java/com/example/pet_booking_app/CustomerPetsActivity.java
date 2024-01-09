package com.example.pet_booking_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.pet_booking_app.Adapters.MyPetAdapter;
import com.example.pet_booking_app.Adapters.PetAdapter;
import com.example.pet_booking_app.models.Pet;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_booking_app.databinding.ActivityCustomerPetsBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CustomerPetsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCustomerPetsBinding binding;

    RecyclerView petsRecyclerView;
    MyPetAdapter petAdapter;
    TextInputEditText edittext_search;
    List<Pet> pets;

    public static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerPetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(Html
                .fromHtml("<font color='#ffffff'> Your pets </font>"));
        getWindow().setStatusBarColor(getResources().getColor(R.color.text_primary)); // Change the color here

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerPetsActivity.this, PetRegister.class);
                startActivity(intent);
            }
        });
        petsRecyclerView = findViewById(R.id.petsRecyclerView);

        pets = new ArrayList<Pet>();

        pets.add(new Pet("Dog", "Buddy", "2015-06-08", "Male", "Golden Retriever", "Golden", "Friendly and active", 1));
        pets.add(new Pet("Cat", "Whiskers", "2017-04-15", "Female", "Siamese", "Cream", "Quiet and independent", 2));
        pets.add(new Pet("Dog", "Max", "2018-01-20", "Male", "Bulldog", "White", "Loyal and protective", 3));
        pets.add(new Pet("Cat", "Bella", "2016-09-30", "Female", "Persian", "Grey", "Affectionate and calm", 1));
        pets.add(new Pet("Dog", "Charlie", "2019-12-05", "Male", "Labrador Retriever", "Black", "Outgoing and even tempered", 2));


        petAdapter = new MyPetAdapter(pets);
        petsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        petsRecyclerView.setAdapter(petAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            /* Clear all login details */
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            settings.edit().clear().commit();

            /* Redirect to login screen */
            Intent intent = new Intent(CustomerPetsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}