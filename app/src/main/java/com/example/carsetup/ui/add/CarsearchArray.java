package com.example.carsetup.ui.add;

public class CarsearchArray {

    // Variables

    private final int year;
    private final String make;
    private final String model;
    private final String type;

    // Constructor

    public CarsearchArray(int year, String make, String model, String type) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
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

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getMake() + " " + getModel() + " " + getYear() + " " + getType();
    }

}
