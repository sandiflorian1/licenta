package com.back4app.quickstartexampleapp;

import java.util.Date;

public class TransportClass {

    private String name, reservNr, depStation, arrStation, note;
    private Number price;
    private Date depDate, arrDate;

    public TransportClass() {
    }

    public TransportClass( String name, String reservNr, String depStation, String arrStation, String note, Number price, Date depDate, Date arrDate) {
        this.name = name;
        this.reservNr = reservNr;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.note = note;
        this.price = price;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Date getArrDate() {
        return arrDate;
    }

    public void setArrDate(Date arrDate) {
        this.arrDate = arrDate;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReservNr() {
        return reservNr;
    }

    public void setReservNr(String reservNr) {
        this.reservNr = reservNr;
    }

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
