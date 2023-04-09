package com.example.ramq.classes;

import android.content.SharedPreferences;
import android.widget.RadioGroup;

public class RatingManager extends RatingManagement{
    private int rating;
    @Override
    public void rateUser(int rating) {
        this.rating = rating;

    }

}