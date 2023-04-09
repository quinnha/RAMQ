package com.example.ramq.classes;

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
//        this.totalFare = totalFare;
        return totalFare;
    }

    @Override
    public double calculateIndivFare(int numPassengers,double distance) {
        double indivFare = calculateTotalFare(distance)/numPassengers;
//        this.indivFare = indivFare;
        return indivFare;
    }

}