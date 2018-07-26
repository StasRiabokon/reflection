package com.mycompany.reflection.task3;

import java.util.Date;

public class User {

    @Save
    private int id;

    @Save
    private String firstName;

    @Save
    private String lastName;

    @Save
    private Date birth;

    @Save
    private String address;


    public User() {
    }

    public User(int id, String firstName, String lastName, Date birth, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
