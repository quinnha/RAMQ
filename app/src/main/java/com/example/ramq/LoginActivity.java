package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramq.classes.AccountInformation;

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;
    private boolean isValid;

    private AccountInformation accountInfo = new AccountInformation("ramq", "ramq");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // loads activity_login.mxl

        // bind variables to xml -> class var = xml id
        eUsername = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

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
                        Toast.makeText(LoginActivity.this, "Incorrect username and/or password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                        // Go to landing page
                        Intent landing = new Intent(LoginActivity.this, LandingActivity.class);
                        startActivity(landing);
                    }
                }


            }
        });


    }

    private boolean validate(String username, String password){
        if (username.equals(accountInfo.getUserName()) && password.equals(accountInfo.getPassword())){
            return true;
        } else{
            return false;
        }
    }
}