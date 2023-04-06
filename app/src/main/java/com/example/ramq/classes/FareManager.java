package com.example.ramq.classes;

public class FareManager extends FareManagement{

    public FareManager(){
        this.fare = 0;
    }

    @Override
    public void calculateFare(double distance, double numPassengers) {
        double dollPerKm = 1.75;
        double basicFee = 4.25;
        double totalFare = basicFee + dollPerKm*distance;
        double indivFare = totalFare/numPassengers;
        this.fare = indivFare;
    }

    @Override
    public double showFare() {
        return this.fare;
    }
}
