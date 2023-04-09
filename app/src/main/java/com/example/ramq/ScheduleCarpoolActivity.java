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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ScheduleCarpoolActivity extends AppCompatActivity
{
    private Button timeButton;
    private Button displayDate;

    private Button nextButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private EditText taxiID;

    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_carpool);
        timeButton = findViewById(R.id.timePicker);

        displayDate = findViewById(R.id.datePicker);

        nextButton = findViewById(R.id.nextScheduleCarpoolButton);

        taxiID = findViewById(R.id.editTextTaxiID);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleCarpoolActivity.this,ScheduleCarpoolSuccessActivity.class);
                SharedPreferences scheduledCarpoolInfo = getSharedPreferences("ScheduledCarpool",MODE_PRIVATE);
                SharedPreferences.Editor sciEdit = scheduledCarpoolInfo.edit();
                Log.d("taxiIDentered", taxiID.toString());
                sciEdit.putString("taxiID",taxiID.getText().toString());
                sciEdit.putString("year",String.valueOf(year));
                sciEdit.putString("month",String.valueOf(month));
                sciEdit.putString("day",String.valueOf(day));
                sciEdit.putString("minute",String.valueOf(minute));
                sciEdit.apply();
                startActivity(intent);
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

//        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}