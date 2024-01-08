package com.example.pet_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupChoiceActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioCustomer;
    private RadioButton radioCaregiver;
    private Button nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_choice);

        radioGroup = (RadioGroup) findViewById(R.id.radiobtngroup_signup_choice);
        radioCustomer = (RadioButton) findViewById(R.id.radiobtn_customer);
        radioCaregiver = (RadioButton) findViewById(R.id.radiobtn_caregiver);
        nextButton = (Button) findViewById(R.id.btn_next);
        backButton = (Button) findViewById(R.id.btn_back);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == radioCustomer.getId()) {
                    Intent intent = new Intent(SignupChoiceActivity.this, SignupCustomerActivity.class);
                    startActivity(intent);
                } else if (selectedId == radioCaregiver.getId()) {
                    Intent intent = new Intent(SignupChoiceActivity.this, SignupCaregiverActivity.class);
                    startActivity(intent);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

    }
}