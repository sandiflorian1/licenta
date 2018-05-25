package com.back4app.quickstartexampleapp;

public class TripClass {
    private String title, startDateTrip, endDateTrip, year;

    public TripClass() {
    }

    public TripClass(String title, String startDateTrip, String endDateTrip, String year) {
        this.title = title;
        this.startDateTrip = startDateTrip;
        this.endDateTrip = endDateTrip;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartDateTrip() {
        return startDateTrip;
    }

    public void setStartDateTrip(String startDateTrip) {
        this.startDateTrip = startDateTrip;
    }
    public String getEndDateTrip() {
        return endDateTrip;
    }

    public void setEndDateTrip(String endDateTrip) {
        this.endDateTrip = endDateTrip;
    }
}
