package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScheduleCarpoolSuccessActivity extends AppCompatActivity {

    private Button backToHome;
    private TextView taxiIDView, timeView, dateView;

    private String taxiID, year, month, day, hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_carpool_success);
        backToHome = findViewById(R.id.backToHomeScheduleCarpool);
        taxiIDView = findViewById(R.id.taxiIDtextView);
        timeView = findViewById(R.id.timeTextView);
        dateView = findViewById(R.id.dateTextView);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleCarpoolSuccessActivity.this,LandingActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        SharedPreferences scheduledCarpoolInfo = getSharedPreferences("ScheduledCarpool",MODE_PRIVATE);

        taxiID = scheduledCarpoolInfo.getString("taxiID","0032");
        year = scheduledCarpoolInfo.getString("year","2000");
        month = scheduledCarpoolInfo.getString("month","12");
        day = scheduledCarpoolInfo.getString("day","25");
        hour = scheduledCarpoolInfo.getString("hour","20");
        minute = scheduledCarpoolInfo.getString("minute","23");

        taxiIDView.setText(taxiID);
        timeView.setText(hour+":"+minute);
        dateView.setText(year+"/"+month+"/"+day);

    }






}