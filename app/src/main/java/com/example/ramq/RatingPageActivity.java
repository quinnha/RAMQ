package com.example.ramq;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramq.classes.RatingManager;

public class RatingPageActivity extends AppCompatActivity{

    private RatingManager ratingManager;
    private TextView ratingPageTitle;
    private TextView ratePrompt;
    private TextView rateeName;
    private RadioGroup groupRadio;
    private Button nextButton;
    private RadioButton selectedRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_page);
        ratingPageTitle = findViewById(R.id.ratingPageTitle);
        ratePrompt = findViewById(R.id.ratePrompt);
        rateeName = findViewById(R.id.rateeName);
        groupRadio = findViewById(R.id.groupRadio);
        nextButton = findViewById(R.id.nextButton);
        ratingManager = new RatingManager();

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedRating = radioGroup.findViewById(i);
                ratingManager.rateUser(Integer.parseInt(selectedRating.getText().toString()));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedID = groupRadio.getCheckedRadioButtonId();
                if (selectedID == -1) {
                    //Rating Error
                    Toast.makeText(RatingPageActivity.this, "No Rating Selected", Toast.LENGTH_SHORT).show();
                } else {
                    //Rating Success
                    //"TODO Go to the next user/next page if at the end of the user array"
                    Toast.makeText(RatingPageActivity.this, "Rating saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
