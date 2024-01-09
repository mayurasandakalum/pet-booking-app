package com.example.pet_booking_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.pet_booking_app.Adapters.BookingsAdapter;
import com.example.pet_booking_app.Adapters.MyPetAdapter;
import com.example.pet_booking_app.databinding.ActivityCaregiverBookingsBinding;
import com.example.pet_booking_app.models.Booking;
import com.example.pet_booking_app.models.DBHelper;
import com.example.pet_booking_app.models.Pet;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_booking_app.databinding.ActivityCustomerPetsBinding;

import java.util.List;

public class CaregiverBookings extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCaregiverBookingsBinding binding;


    RecyclerView petsRecyclerView;
    BookingsAdapter bookingAdapter;
    List<Booking> bookings;

    public static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCaregiverBookingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(Html
                .fromHtml("<font color='#ffffff'> Your bookings </font>"));
        getWindow().setStatusBarColor(getResources().getColor(R.color.text_primary)); // Change the color here

        petsRecyclerView = findViewById(R.id.petsRecyclerView);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int ownerId = prefs.getInt("userId", -1);

        // Fetch the pets from the database
        DBHelper dbHelper = new DBHelper(CaregiverBookings.this);
        bookings = dbHelper.getBookingsForCaregiver(ownerId);

        Log.d("bookingsownerId", "onCreate: " + ownerId);

        bookingAdapter = new BookingsAdapter(bookings);
        petsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        petsRecyclerView.setAdapter(bookingAdapter);

        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverBookings.this, HomeActivity.class);
                startActivity(intent);
            }
        });

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
            Intent intent = new Intent(CaregiverBookings.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_customer_pets);
////        return NavigationUI.navigateUp(navController, appBarConfiguration)
////                || super.onSupportNavigateUp();
//    }
}