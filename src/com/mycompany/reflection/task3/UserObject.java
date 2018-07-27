package com.mycompany.reflection.task3;

import java.util.Date;

public class UserObject {

    @Save
    private String stringField;

    private int intField;

    private Date dateField;

    @Save
    private ObjectItem objectItem;

    public UserObject() {
    }

    public UserObject(String stringField, int intField, Date dateField, ObjectItem objectItem) {
        this.stringField = stringField;
        this.intField = intField;
        this.dateField = dateField;
        this.objectItem = objectItem;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public ObjectItem getObjectItem() {
        return objectItem;
    }

    public void setObjectItem(ObjectItem objectItem) {
        this.objectItem = objectItem;
    }
}
