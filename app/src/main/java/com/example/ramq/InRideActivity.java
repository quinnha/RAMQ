/*
Sources:
https://learnoset.com/source-code/android-studio-examples/how-to-create-a-countdown-timer-in-android-studio
 */

package com.example.ramq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InRideActivity extends AppCompatActivity {

    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_ride);

        final TextView hour = findViewById(R.id.hour);
        final TextView min = findViewById(R.id.min);
        final TextView seconds = findViewById(R.id.second);
        final AppCompatButton endRideButton = findViewById(R.id.endRideButton);

        SharedPreferences tripInfo = getSharedPreferences("TripInformation",MODE_PRIVATE);
        duration = Integer.parseInt(tripInfo.getString("duration","20"));


        endRideButton.setEnabled(false);
        endRideButton.setAlpha(0.2f);

        new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // getting time format on HH:MM:SS
                        String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                        final String[] hourMinSec = time.split(":");

                        hour.setText(hourMinSec[0]);
                        min.setText(hourMinSec[1]);
                        seconds.setText(hourMinSec[2]);
                    }
                });
            }

            @Override
            public void onFinish() {
                endRideButton.setAlpha(1f);
                endRideButton.setEnabled(true);
            }
        }.start();

        endRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(InRideActivity.this,"Button Clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InRideActivity.this,FareActivity.class);
                startActivity(intent);
            }
        });

    }



}