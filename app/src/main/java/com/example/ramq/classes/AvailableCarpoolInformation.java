package com.example.ramq.classes;

public class AvailableCarpoolInformation {
    private String cabName;
    private int numPassengers;
    private int seatsAvaiable;

    private String qrCodeAssociated;

    public String getCabName() {
        return cabName;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public int getSeatsAvaiable() {
        return seatsAvaiable;
    }

    public String getQrCodeAssociated(){
        return qrCodeAssociated;
    }

    public AvailableCarpoolInformation(String cabName, int numPassengers, int seatsAvaiable, String qrCodeAssociated ){
        this.cabName = cabName;
        this.numPassengers = numPassengers;
        this.seatsAvaiable = seatsAvaiable;
        this.qrCodeAssociated = qrCodeAssociated;
    }
}

