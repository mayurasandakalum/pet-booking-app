package com.example.pet_booking_app.models;

public class Booking {
    private int bookingId;
    private int caregiverId;
    private int petId;
    private String petName;

    // All-argument constructor
    public Booking(int bookingId, int caregiverId, int petId, String petName) {
        this.bookingId = bookingId;
        this.caregiverId = caregiverId;
        this.petId = petId;
        this.petName = petName;
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public int getCaregiverId() {
        return caregiverId;
    }

    public int getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setCaregiverId(int caregiverId) {
        this.caregiverId = caregiverId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}