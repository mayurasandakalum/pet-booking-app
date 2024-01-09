package com.example.pet_booking_app.models;

public class Pet {
    private int id;
    private String type;
    private String name;
    private String birthday;
    private String gender;
    private String breed;
    private String color;
    private String otherDetails;
    private int ownerId;
    private String isBooked;

    // constructors
    public Pet(String type, String name, String birthday, String gender, String breed, String color, String otherDetails, int ownerId, String isBooked) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        this.otherDetails = otherDetails;
        this.ownerId = ownerId;
        this.isBooked = isBooked;
    }

    public Pet(int id, String type, String name, String birthday, String gender, String breed, String color, String otherDetails, int ownerId, String isBooked) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        this.otherDetails = otherDetails;
        this.ownerId = ownerId;
        this.isBooked = isBooked;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }
}