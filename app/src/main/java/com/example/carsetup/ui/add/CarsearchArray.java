package com.example.carsetup.ui.add;

public class CarsearchArray {

    // Variables
    private final int id;
    private final String make;
    private final String model;
    private final int year;
    private final String type;

    // Constructor

    public CarsearchArray(int id, String make, String model, int year, String type) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
    }

    // Get & Setters

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getId() + " " + getMake() + " " + getModel() + " " + getYear() + " " + getType();
    }

}
