package com.back4app.quickstartexampleapp;

public class AccommodationClass {

    private String name, reservNr, address, checkin, checkout, note, price ;

    public AccommodationClass() {
    }

    public AccommodationClass(String name, String reservNr, String address, String note, String price, String checkin, String checkout) {
        this.name = name;
        this.reservNr = reservNr;
        this.address = address;
        this.checkin = checkin;
        this.checkout = checkout;
        this.note = note;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}