package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ramq.classes.FareManager;
import com.example.ramq.classes.RatingManager;

public class FareActivity extends AppCompatActivity {

    FareManager concreteFareManager = new FareManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);
    }
}