/**
 * Source : https://www.youtube.com/watch?v=t8nGh4gN1Q0
 */
package com.example.ramq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;


public class JoinCarpoolActivity extends AppCompatActivity {

    //unique key for the intent (uniqueness in name convention to avoid duplicates)
    public static final String PICKUP = "com.example.ramq.JoinCarpool.PICKUP";
    public static final String DEST = "com.example.ramq.JoinCarpool.DEST";
    private final int PICKUP_LOCATION_REQUEST_CODE = 100;
    private final int DESTINATION_REQUEST_CODE = 101;

    private EditText pickupLocation;
    private EditText destination;

    private Button nextButton;

    //[Longitude, Latitude]
    //hardcoding for testing purpose
//    new LatLng(43.257497, -79.928025)
//    new LatLng(43.257807, -79.914485)
    private LatLng pickUpLngLat;
    private LatLng destLngLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);


        pickupLocation = findViewById(R.id.editTextPickup);
        destination = findViewById(R.id.editTextDestination);

        nextButton = findViewById(R.id.nextJoinCarpoolButton);

        //Initialize places
        Places.initialize(getApplicationContext(),"replace this with ur own key");

        //Setting pickupLocation and destination EditTexts to non focusable
        pickupLocation.setFocusable(false);
        destination.setFocusable(false);

        pickupLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initializing place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG,Place.Field.NAME);

                //Creating Intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList).build(JoinCarpoolActivity.this);

                //starting activity result for pickup location
                startActivityForResult(intent,PICKUP_LOCATION_REQUEST_CODE);
            }
        });


        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initializing place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG,Place.Field.NAME);

                //Creating Intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList).build(JoinCarpoolActivity.this);

                //starting activity result for pickup location
                startActivityForResult(intent,DESTINATION_REQUEST_CODE);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destLngLat != null && pickUpLngLat != null) {
                    Intent intent = new Intent(JoinCarpoolActivity.this, MapsActivity.class);
                    intent.putExtra("origin","JoinCarpool");
                    intent.putExtra("PICKUP",pickUpLngLat);
                    intent.putExtra("DEST",destLngLat);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICKUP_LOCATION_REQUEST_CODE && resultCode == RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            pickUpLngLat = place.getLatLng();
            pickupLocation.setText(place.getAddress());

        } else if (requestCode == DESTINATION_REQUEST_CODE && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            destLngLat = place.getLatLng();
            destination.setText(place.getAddress());

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(),status.getStatusMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}