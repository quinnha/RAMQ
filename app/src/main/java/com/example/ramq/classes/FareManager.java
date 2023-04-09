package com.example.ramq.classes;

import java.text.DecimalFormat;

public class FareManager extends FareManagement{

    public FareManager(){
        this.totalFare = 0;
        this.indivFare = 0;
    }

    @Override
    public double calculateTotalFare(double distance) {
        double dollPerKm = 1.75;
        double basicFee = 4.25;
        double totalFare = basicFee + dollPerKm*distance/1000;
        return Math.round(totalFare*100.0)/100.0;
    }

    @Override
    public double calculateIndivFare(int numPassengers,double distance) {
        double indivFare = calculateTotalFare(distance)/numPassengers;
        return Math.round(indivFare*100.0)/100.0;
    }

}