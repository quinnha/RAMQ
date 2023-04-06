package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramq.classes.AccountInformation;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private EditText eEmail;
    private EditText ePhone;
    private Button eRegister;
    private TextView eLogin;

    public static AccountInformation accountInfo;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // bind variables to xml -> class var = xml id
        eUsername = findViewById(R.id.etRUsername);
        ePassword = findViewById(R.id.etRPassword);
        eEmail = findViewById(R.id.etREmail);
        ePhone = findViewById(R.id.etRPhone);
        eRegister = findViewById(R.id.btnRegister);
        eLogin = findViewById(R.id.tvRLogin);

        // local account storage stuff
        sharedPreferences = getApplicationContext().getSharedPreferences("AccountDB", MODE_PRIVATE); //Stored on phone
        sharedPreferencesEditor = sharedPreferences.edit(); // Can add values to file in XML format

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUsername = eUsername.getText().toString();
                String regPassword = ePassword.getText().toString();
                String regEmail = eEmail.getText().toString();
                String regPhone = ePhone.getText().toString();

                if (validate(regUsername, regPassword, regEmail, regPhone)){
                    accountInfo = new AccountInformation(regUsername, regPassword, "001", regEmail, regPhone);

                    // Store in AccountDB
                    sharedPreferencesEditor.putString("username", regUsername);
                    sharedPreferencesEditor.putString("password", regPassword);
                    sharedPreferencesEditor.putString("userID", "001");
                    sharedPreferencesEditor.putString("email", regEmail);
                    sharedPreferencesEditor.putString("phone", regPhone);

                    // Apply into the AccountDB
                    sharedPreferencesEditor.apply();

                    // Go to login
                    Intent login = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(login);
                    Toast.makeText(RegistrationActivity.this, "Registration success!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to login page
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }

    private boolean validate(String username, String password, String email, String phone){
        // validation

        return true;
    }
}