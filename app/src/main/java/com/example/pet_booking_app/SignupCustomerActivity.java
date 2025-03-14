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

import com.example.pet_booking_app.models.Customer;
import com.example.pet_booking_app.models.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

public class SignupCustomerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Declaration of instance variables
    EditText birthday;
    TextInputEditText fullName, address, phone, email, password, confirmPassword;
    AutoCompleteTextView gender;
    Button signup, login;
    DBHelper DB;

    ArrayAdapter<String> adapterItems;

    String[] items = {"Male", "Female"};


    // Method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_customer);

        // Initialize UI elements by finding them in the layout
        birthday = (EditText) findViewById(R.id.edittext_date);
        gender = findViewById(R.id.autocomplete_gender);

        fullName = findViewById(R.id.edittext_fullname);
        address = findViewById(R.id.edittext_address);
        birthday = findViewById(R.id.edittext_date);
        phone = findViewById(R.id.edittext_phone);
        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_pass);
        confirmPassword = findViewById(R.id.edittext_pass_confirm);
        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.btn_login);

        DB = new DBHelper(this);

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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameVal = fullName.getText().toString();
                String addressVal = address.getText().toString();
                String birthdayVal = birthday.getText().toString();
                String genderVal = gender.getText().toString();
                String phoneVal = phone.getText().toString();
                String emailVal = email.getText().toString();
                String passwordVal = password.getText().toString();
                String confirmPasswordVal = confirmPassword.getText().toString();

                if (passwordVal.equals(confirmPasswordVal)) {
                    Customer customer = new Customer(fullNameVal, addressVal, birthdayVal, genderVal, phoneVal, emailVal, passwordVal);
                    boolean check = DB.signupCustomer(customer);
                    if (check) {
                        Toast.makeText(SignupCustomerActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        // Create an Intent to start LoginActivity
                        Intent intent = new Intent(SignupCustomerActivity.this, LoginActivity.class);
                        // Start the LoginActivity
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupCustomerActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupCustomerActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start LoginActivity
                Intent intent = new Intent(SignupCustomerActivity.this, LoginActivity.class);
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
