package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    TextView eViewAccount;
    TextView eCreateCarpool;
    TextView eJoinCarpool;
    TextView eScheduleCarpool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        eCreateCarpool = findViewById(R.id.tvLCreateCarpool);
        eJoinCarpool = findViewById(R.id.tvLJoinCarpool);
        eScheduleCarpool = findViewById(R.id.tvLScheduleCarpool);
        eViewAccount = findViewById(R.id.tvLViewAccount);


        // Click to view account
        eViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, ViewAccountActivity.class));
            }
        });

        // Click to create a carpool
        eCreateCarpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, CreateCarpoolActivity.class));
            }
        });

        // Click to join a carpool
        eJoinCarpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, JoinCarpoolActivity.class));
            }
        });

        //Click to schedule a carpool
        eScheduleCarpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, ScheduleCarpoolActivity.class));
            }
        });


    }
}