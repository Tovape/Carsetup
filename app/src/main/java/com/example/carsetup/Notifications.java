package com.example.carsetup;

public class Notifications {

    // Variables

    private final int type;
    private final String title;
    private final String subtitle;

    // Constructor

    public Notifications(int type, String title, String subtitle) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
    }

    // Get & Setters

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

}
