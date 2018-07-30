package com.mycompany.reflection.task3.models;


import com.mycompany.reflection.task3.annotations.Save;

public class ObjectItem {
    @Save
    private String stringItem;

    @Save
    private int intItem;

    public ObjectItem() {
    }

    public ObjectItem(String stringItem, int intItem) {
        this.stringItem = stringItem;
        this.intItem = intItem;
    }

    public String getStringItem() {
        return stringItem;
    }

    public void setStringItem(String stringItem) {
        this.stringItem = stringItem;
    }

    public int getIntItem() {
        return intItem;
    }

    public void setIntItem(int intItem) {
        this.intItem = intItem;
    }
}
