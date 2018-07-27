package com.mycompany.reflection.task3;

import java.util.Date;
import java.util.List;

@SaveTo
public class User {

    @Save
    private int id;

    @Save
    private String firstName;

    private String lastName;

    @Save
    private Date birth;

    private String address;

    @Save
    private UserObject userObject;

    @Save
    private List<String> list;


    public User() {
    }

    public User(int id, String firstName, String lastName, Date birth, String address, UserObject userObject, List<String> list) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.address = address;
        this.userObject = userObject;
        this.list = list;
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

    public UserObject getUserObject() {
        return userObject;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
