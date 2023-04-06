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
import com.example.ramq.classes.EncryptionManager;

public class EditAccountActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private EditText eEmail;
    private EditText ePhone;
    private Button eEdit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        // bind variables to xml -> class var = xml id
        eUsername = findViewById(R.id.etEUsername);
        ePassword = findViewById(R.id.etEPassword);
        eEmail = findViewById(R.id.etEEmail);
        ePhone = findViewById(R.id.etEPhone);
        eEdit = findViewById(R.id.btnEdit);

        // local account storage stuff
        sharedPreferences = getApplicationContext().getSharedPreferences("AccountDB", MODE_PRIVATE); //Stored on phone
        sharedPreferencesEditor = sharedPreferences.edit(); // Can add values to file in XML format

        eEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUsername = eUsername.getText().toString();
                String regPassword = ePassword.getText().toString();
                String regEmail = eEmail.getText().toString();
                String regPhone = ePhone.getText().toString();

                if (validate(regUsername, regPassword, regEmail, regPhone)){
                    RegistrationActivity.accountInfo = new AccountInformation(regUsername, regPassword, "001", regEmail, regPhone);

                    // Store in AccountDB
                    sharedPreferencesEditor.putString("username", regUsername);
                    sharedPreferencesEditor.putString("password", regPassword);
                    sharedPreferencesEditor.putString("userID", "001");
                    sharedPreferencesEditor.putString("email", regEmail);
                    sharedPreferencesEditor.putString("phone", regPhone);

                    // Apply into the AccountDB
                    sharedPreferencesEditor.apply();

                    // Go to account info
                    startActivity(new Intent(EditAccountActivity.this, ViewAccountActivity.class));
                    Toast.makeText(EditAccountActivity.this, "Edit Account Success!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private boolean validate(String username, String password, String email, String phone){
        // validation

        return true;
    }
}