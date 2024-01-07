package com.example.pet_booking_app.models;

public class Caregiver {
    private int id;
    private String fullName;
    private String address;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String password;

    public Caregiver(int id, String full_name, String address, String birthday, String gender, String phone_number, String email, String password) {
        this.id = id;
        this.fullName = full_name;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phone_number;
        this.email = email;
        this.password = password;
    }

    public Caregiver(String full_name, String address, String birthday, String gender, String phone_number, String email, String password) {
        this.fullName = full_name;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phone_number;
        this.email = email;
        this.password = password;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}