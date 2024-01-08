package com.example.pet_booking_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pet_booking_app.models.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends Activity {

    TextInputEditText email, password;
    Button login, signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_pass);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);

        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailVal = email.getText().toString();
                String passwordVal = password.getText().toString();

                Object result = DB.login(emailVal, passwordVal);

                if (result instanceof Boolean && !(Boolean) result) {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Logged in as " + result, Toast.LENGTH_SHORT).show();
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
