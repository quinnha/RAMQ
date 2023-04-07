package com.example.ramq.classes;

public class FareManager extends FareManagement{

    public FareManager(){
        this.totalFare = 0;
        this.indivFare = 0;
    }

    @Override
    public void calculateTotalFare(double distance) {
        double dollPerKm = 1.75;
        double basicFee = 4.25;
        double totalFare = basicFee + dollPerKm*distance;
        this.totalFare = totalFare;
    }

    @Override
    public void calculateIndivFare(double numPassengers) {
        double indivFare = this.totalFare/numPassengers;
        this.indivFare = indivFare;
    }

    @Override
    public double showTotalFare() {
        return this.totalFare;
    }

    @Override
    public double showIndivFare() {
        return this.indivFare;
    }
}
