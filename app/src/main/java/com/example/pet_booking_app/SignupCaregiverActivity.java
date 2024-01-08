package com.example.pet_booking_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.pet_booking_app.models.Caregiver;
import com.example.pet_booking_app.models.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

public class SignupCaregiverActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Declaration of instance variables
    EditText birthday;
    String[] items = {"Male", "Female"};
    AutoCompleteTextView gender;
    ArrayAdapter<String> adapterItems;

    private TextInputEditText fullName, address, phone, email, password, confirmPassword;
    private DBHelper dbHelper;
    Button nextButton, login;

    // Method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_caregiver);

        // Initialize UI elements by finding them in the layout
        birthday = (EditText) findViewById(R.id.edittext_date);
        gender = findViewById(R.id.auto_complete_gender);
        fullName = findViewById(R.id.edittext_fullname);
        address = findViewById(R.id.edittext_address);
        gender = findViewById(R.id.auto_complete_gender);
        phone = findViewById(R.id.edittext_phone);
        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_pass);
        confirmPassword = findViewById(R.id.edittext_pass_confirm);

        nextButton = findViewById(R.id.btn_next);
        login = findViewById(R.id.btn_login);

        dbHelper = new DBHelper(this);

        // Set an OnClickListener for the date EditText to show a DatePickerDialog
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // Initialize an ArrayAdapter for the AutoCompleteTextView with predefined items
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        gender.setAdapter(adapterItems);

        // Set an OnItemClickListener for the AutoCompleteTextView to display a toast when an item is selected
        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullnameText = fullName.getText().toString();
                String addressText = address.getText().toString();
                String dateText = birthday.getText().toString();
                String genderText = gender.getText().toString();
                String phoneText = phone.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();

                if (passwordText.equals(confirmPasswordText)) {
                    Caregiver caregiver = new Caregiver(fullnameText, addressText, dateText, genderText, phoneText, emailText, passwordText);
                    boolean isInserted = dbHelper.signupCaregiver(caregiver);
                    if (isInserted) {
                        Toast.makeText(SignupCaregiverActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignupCaregiverActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SignupCaregiverActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start LoginActivity
                Intent intent = new Intent(SignupCaregiverActivity.this, LoginActivity.class);
                // Start the LoginActivity
                startActivity(intent);
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
        birthday.setText(currentDateString, TextView.BufferType.EDITABLE);
    }
}
