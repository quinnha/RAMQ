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

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;
    private TextView eRegister;
    private boolean isValid;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // loads activity_login.mxl

        // bind variables to xml -> class var = xml id
        eUsername = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.tvRegister);

        sharedPreferences = getApplicationContext().getSharedPreferences("AccountDB", MODE_PRIVATE); //Stored on phone

        // If a account already exists locally (stored in db), make the accountinfo object
        if (sharedPreferences != null){
            String savedUsername = sharedPreferences.getString("username", "ramq");
            String savedPassword = sharedPreferences.getString("password", "ramq");
            String savedUserID = sharedPreferences.getString("userID", "001");
            String savedEmail = sharedPreferences.getString("email", "ramq@ramq.com");
            String savedPhone = sharedPreferences.getString("phone", "1234567890");
            RegistrationActivity.accountInfo = new AccountInformation(savedUsername, savedPassword, savedUserID, savedEmail, savedPhone);
        }

        // onclick listener for the button (i love autosuggestion)
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = eUsername.getText().toString();
                String inputPassword = ePassword.getText().toString();

                // Error Message
                if (inputUsername.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validate(inputUsername, inputPassword);

                    if (!isValid){
                        // Display error message and clear lines
                        Toast.makeText(LoginActivity.this, "Incorrect username and/or password", Toast.LENGTH_SHORT).show();
                        eUsername.setText("");
                        ePassword.setText("");
                    } else {
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                        // Go to landing page
                        Intent landing = new Intent(LoginActivity.this, LandingActivity.class);
                        startActivity(landing);
                    }
                }


            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register page
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });


    }

    private boolean validate(String username, String password){

        if(RegistrationActivity.accountInfo != null){
            if (username.equals(RegistrationActivity.accountInfo.getUserName()) && password.equals(RegistrationActivity.accountInfo.getPassword())){
                return true;
            } else{
                return false;
            }
        }

        return false;
    }
}