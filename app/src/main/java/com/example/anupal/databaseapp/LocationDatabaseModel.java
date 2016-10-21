package com.example.anupal.databaseapp;

/**
 * Created by anupal on 21/10/16.
 */

public class LocationDatabaseModel {
    private String address;
    private String description;
    private String type;
    private String contactNo;
    private String rating;
    private String name;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getRating() {
        return rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }
}
