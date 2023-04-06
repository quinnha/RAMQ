package com.example.ramq.classes;

public abstract class FareManagement {

    protected double fare;

    public abstract void calculateFare(double distance, double numPassengers);

    public abstract double showFare();

}
