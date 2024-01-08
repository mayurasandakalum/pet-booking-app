package com.example.pet_booking_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pet_booking_app.models.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

public class LoginActivity extends Activity {

    TextInputEditText email, password;
    Button login, signup;
    DBHelper DB;

    public static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Check if we successfully logged in before.
         * If we did, redirect to home page */
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            String userType = settings.getString("userType", "");
            Intent intent;
            if ("customer".equals(userType)) {
                intent = new Intent(LoginActivity.this, CustomerPetsActivity.class);
            } else if ("caregiver".equals(userType)) {
                intent = new Intent(LoginActivity.this, HomeActivity.class);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid user type", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(intent);
        }

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_pass);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);

        DB = new DBHelper(this);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String emailVal = email.getText().toString();
//                String passwordVal = password.getText().toString();
//
//                Object result = DB.login(emailVal, passwordVal);
//
//                if (result instanceof Boolean && !(Boolean) result) {
//                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Logged in as " + result, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailVal = email.getText().toString();
                String passwordVal = password.getText().toString();

                // Assuming DB.login returns the type of user if login is successful
                Map<String, Object> result = DB.login(emailVal, passwordVal);

                if (result == null) {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else {
                    String userType = (String) result.get("userType");
                    Integer userId = (Integer) result.get("id");

                    /* User successfully logged in, save login status in SharedPreferences */
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("logged", "logged");
                    editor.putString("userType", userType);
                    editor.putInt("userId", userId);
                    editor.commit();

                    /* Redirect to different activities based on user type */
                    Intent intent;
                    if ("customer".equals(userType)) {
                        intent = new Intent(LoginActivity.this, CustomerPetsActivity.class);
                    } else if ("caregiver".equals(userType)) {
                        intent = new Intent(LoginActivity.this, HomeActivity.class);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid user type", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(intent);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle signup click
                Intent intent = new Intent(LoginActivity.this, SignupChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
