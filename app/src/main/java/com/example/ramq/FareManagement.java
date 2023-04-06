package com.example.ramq;

public abstract class FareManagement {

    protected double fare;

    public abstract void calculateFare(double distance, double numPassengers);

    public abstract double showFare();

}
