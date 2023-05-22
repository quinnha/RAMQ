/**
 * Sources:
 * https://github.com/codeWithCal/TimePickerAndroidStudio
 * https://github.com/mitchtabian/DatePickerDialog
 */
package com.example.ramq;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.List;

public class ScheduleCarpoolActivity extends AppCompatActivity
{
    private final int PICKUP_LOCATION_REQUEST_CODE = 100;
    private final int DESTINATION_REQUEST_CODE = 101;

    private Button timeButton;
    private Button displayDate;

    private Button nextButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private EditText taxiID;

    private int year, month, day, hour, minute;

    private EditText pickupLocation;
    private EditText destination;

    private LatLng pickUpLngLat;
    private LatLng destLngLat;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_carpool);

        pickupLocation = findViewById(R.id.editTextPickup);
        destination = findViewById(R.id.editTextDestination);

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
                        fieldList).build(ScheduleCarpoolActivity.this);

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
                        fieldList).build(ScheduleCarpoolActivity.this);

                //starting activity result for pickup location
                startActivityForResult(intent,DESTINATION_REQUEST_CODE);
            }
        });


        timeButton = findViewById(R.id.timePicker);

        displayDate = findViewById(R.id.datePicker);

        nextButton = findViewById(R.id.nextScheduleCarpoolButton);

        taxiID = findViewById(R.id.editTextTaxiID);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destLngLat != null && pickUpLngLat != null) {
                    Intent intent = new Intent(ScheduleCarpoolActivity.this, ScheduleCarpoolSuccessActivity.class);
                    SharedPreferences scheduledCarpoolInfo = getSharedPreferences("ScheduledCarpool", MODE_PRIVATE);
                    SharedPreferences.Editor sciEdit = scheduledCarpoolInfo.edit();
                    Log.d("taxiIDentered", taxiID.toString());
                    sciEdit.putString("taxiID", taxiID.getText().toString());
                    sciEdit.putString("year", String.valueOf(year));
                    sciEdit.putString("month", String.valueOf(month));
                    sciEdit.putString("day", String.valueOf(day));
                    sciEdit.putString("minute", String.valueOf(minute));
                    sciEdit.apply();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Location Input",Toast.LENGTH_SHORT).show();
                }
            }
        });

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(ScheduleCarpoolActivity.this,
                        android.R.style.Theme_Material_Dialog,dateSetListener,
                        year,month,day);
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                month = mm+1;
                year = yy;
                day = dd;
                System.out.println("year: "+year+" month: "+month+" day: "+day);
            }
        };

    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                System.out.println("hour: "+hour+" minute: "+minute);
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Material_Dialog, onTimeSetListener, hour, minute, true);

        timePickerDialog.show();
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