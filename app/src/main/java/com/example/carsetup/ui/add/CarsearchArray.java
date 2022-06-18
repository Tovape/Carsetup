package com.example.carsetup.ui.add;

public class CarsearchArray {

    // Variables

    private final int year;
    private final String make;
    private final String model;

    // Constructor

    public CarsearchArray(int year, String make, String model) {
        this.year = year;
        this.make = make;
        this.model = model;
    }

    // Get & Setters

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return getMake() + " " + getModel() + " " + getYear();
    }

}
