package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramq.classes.FareManagement;
import com.example.ramq.classes.FareManager;

public class FareActivity extends AppCompatActivity {
    private FareManagement fareManager = new FareManager();

    private TextView distaneText;
    private TextView numPassengers;

    private TextView total;

    private TextView youOwe;

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);

        distaneText = findViewById(R.id.distanceTextValueTV);
        numPassengers = findViewById(R.id.numPassengersValueTV);
        total = findViewById(R.id.totalValueTV);
        youOwe = findViewById(R.id.oweValueTV);
        nextButton = findViewById(R.id.nextFareButton);

        SharedPreferences tripInfo = getSharedPreferences("TripInformation",MODE_PRIVATE);

        int numberOfPassengers = Integer.parseInt(tripInfo.getString("numPassengers","1"));
        double distanceTravelled = Double.parseDouble(tripInfo.getString("distance","1.097"));

        distaneText.setText(tripInfo.getString("distanceText","1.1Km"));
        numPassengers.setText(String.valueOf(numberOfPassengers));
        total.setText(String.valueOf(fareManager.calculateTotalFare(distanceTravelled)));
        youOwe.setText(String.valueOf(fareManager.calculateIndivFare(numberOfPassengers,distanceTravelled)));


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOfPassengers<=1){
                    startActivity(new Intent(FareActivity.this,EndRideActivity.class));
                }
                else{
                    startActivity(new Intent(FareActivity.this,RatingPageActivity.class));
                }
            }
        });


//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        nextButton = findViewById(R.id.nextFareButton);
//
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(bundle!=null){
//                    String origin = bundle.getString("originCalc");
//
//                    if(origin!=null){
//                        if (origin.equals("CreateCarpool")){
//
//                        }
//                        else if(origin.equals("JoinCarpool")){
//                        }
//
//                        else if(origin.equals("ScheduleCarpool")){
//                        }
//                    }
//
//                }
//            }
//        });
//
//
//        if(bundle!=null){
//            String origin = bundle.getString("originCalc");
//
//            if(origin!=null){
//                if (origin.equals("CreateCarpool")){
//
//                    numPassengers.setText("1");
//                    total.setText(String.valueOf(fareManager.calculateTotalFare(Double.parseDouble(bundle.getString("distance")))));
//                    youOwe.setText(String.valueOf(fareManager.calculateTotalFare(Double.parseDouble(bundle.getString("distance")))));
//                }
//                else if(origin.equals("JoinCarpool")){
//                    numPassengers.setText(bundle.getString("numPassengers"));
//                    total.setText(String.valueOf(fareManager.calculateTotalFare(Double.parseDouble(bundle.getString("distance")))));
//                    youOwe.setText(String.valueOf(fareManager.calculateIndivFare(Integer.parseInt(bundle.getString("numPassengers")),Double.parseDouble(bundle.getString("distance")))));
//                }
//
//                else if(origin.equals("ScheduleCarpool")){
//                    numPassengers.setText("1");
//                    total.setText(String.valueOf(fareManager.calculateTotalFare(Double.parseDouble(bundle.getString("distance")))));
//                    youOwe.setText(String.valueOf(fareManager.calculateTotalFare(Double.parseDouble(bundle.getString("distance")))));
//                }
//            }
//
//        }

    }
}