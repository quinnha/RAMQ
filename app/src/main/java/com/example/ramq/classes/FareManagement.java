package com.example.ramq.classes;

public abstract class FareManagement {

    protected double totalFare;
    protected double indivFare;

    public abstract  double calculateTotalFare(double distance);
    public abstract double calculateIndivFare(int numPassengers,double distance);

}