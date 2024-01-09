package com.example.pet_booking_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.pet_booking_app.models.DBHelper;
import com.example.pet_booking_app.models.Pet;

import java.text.DateFormat;
import java.util.Calendar;

public class PetRegister extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Declaration of instance variables
    EditText editTextName, editTextBreed, editTextColor, editTextOtherDetails, editTextDate;
    AutoCompleteTextView autocomplete_gender;
    RadioGroup radioGroupType;
    Button buttonAdd;
    String[] items = {"Male", "Female"};
    ArrayAdapter<String> adapterItems;
    private RadioButton radioDog, radioCat;

    public static final String PREFS_NAME = "LoginPrefs";

    // Method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        // Initialize UI elements by finding them in the layout
        editTextName = findViewById(R.id.edittext_name);
        editTextDate = findViewById(R.id.edittext_date);
        editTextBreed = findViewById(R.id.edittext_breed);
        editTextColor = findViewById(R.id.edittext_color);
        editTextOtherDetails = findViewById(R.id.edittext_otherdetails);
        autocomplete_gender = findViewById(R.id.auto_complete_gender);
        radioGroupType = findViewById(R.id.radiobtngroup_pet);
        buttonAdd = findViewById(R.id.btn_add);

        radioDog = (RadioButton) findViewById(R.id.radiobtn_dog);
        radioCat = (RadioButton) findViewById(R.id.radiobtn_cat);

        // Set an OnClickListener for the date EditText to show a DatePickerDialog
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // Initialize an ArrayAdapter for the AutoCompleteTextView with predefined items
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autocomplete_gender.setAdapter(adapterItems);

        // Set an OnItemClickListener for the AutoCompleteTextView to display a toast when an item is selected
        autocomplete_gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        // Set an OnClickListener on the buttonAdd button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String date = editTextDate.getText().toString();
                String breed = editTextBreed.getText().toString();
                String color = editTextColor.getText().toString();
                String otherDetails = editTextOtherDetails.getText().toString();
                String gender = autocomplete_gender.getText().toString();

//                int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
//                String type = ((RadioButton)findViewById(selectedTypeId)).getText().toString();

                int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
                String type = "";

                if (selectedTypeId == radioDog.getId()) {
                    type = "Dog";
                } else if (selectedTypeId == radioCat.getId()) {
                    type = "Cat";
                }

                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                int ownerId = prefs.getInt("userId", -1);

                Pet pet = new Pet(name, type, gender, date, breed, color, otherDetails, ownerId);

                Log.d("PetRegister", "Pet Details: " + "Name: " + name + ", Type: " + type + ", Gender: " + gender + ", Date: " + date + ", Breed: " + breed + ", Color: " + color + ", Other Details: " + otherDetails + ", Owner ID: " + ownerId);

                DBHelper dbHelper = new DBHelper(PetRegister.this);
                boolean success = dbHelper.registerPet(pet);

                if (success) {
                    Toast.makeText(PetRegister.this, "Pet added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PetRegister.this, CustomerPetsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PetRegister.this, "Error adding pet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Callback method invoked when a date is set in the DatePickerDialog
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Create a Calendar instance and set the selected date
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // Format the selected date and set it to the TextView and EditText
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        editTextDate.setText(currentDateString, TextView.BufferType.EDITABLE);
    }
}
