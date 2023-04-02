package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewAccountActivity extends AppCompatActivity {

    private TextView eUsername;
    private TextView eEmail;
    private TextView ePhone;
    private TextView eLoadUsername;
    private TextView eLoadEmail;
    private TextView eLoadPhone;
    private TextView eEdit;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        //bind variables
        eUsername = findViewById(R.id.tvAUsername);
        eEmail = findViewById(R.id.tvAEmail);
        ePhone = findViewById(R.id.tvAPhone);

        eLoadUsername = findViewById(R.id.tvAEUsername);
        eLoadEmail = findViewById(R.id.tvAEEmail);
        eLoadPhone = findViewById(R.id.tvAEPhone);

        eEdit = findViewById(R.id.tvAEditAccount);

        sharedPreferences = getApplicationContext().getSharedPreferences("AccountDB", MODE_PRIVATE); //Stored on phone

        // set values from shared preferences
        String savedUsername = sharedPreferences.getString("username", "ramq");
        String savedEmail = sharedPreferences.getString("email", "ramq@ramq.com");
        String savedPhone = sharedPreferences.getString("phone", "1234567890");

        eLoadUsername.setText(savedUsername);
        eLoadEmail.setText(savedEmail);
        eLoadPhone.setText(savedPhone);

        eEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewAccountActivity.this, EditAccountActivity.class));

            }
        });

        // onclick for edit account
    }
}