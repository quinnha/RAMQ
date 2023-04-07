package com.example.ramq.classes;

public abstract class FareManagement {

    protected double totalFare;
    protected double indivFare;

    public abstract void calculateTotalFare(double distance);

    public abstract void calculateIndivFare(double numPassengers);

    public abstract double showTotalFare();

    public abstract double showIndivFare();

}
