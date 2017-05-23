package com.example.tnuv.work_in_progress;

/**
 * Created by CaptainBat on 23. 05. 2017.
 */

public class Category {

    //private variables
    private int id;
    private String name;
    private String color;
    private String location;

    // Empty constructor
    public Category(){
    }

    // constructor
    public Category(int id, String name, String color, String location) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.location = location;
    }

    // constructor
    public Category(String name, String color, String location) {
        this.name = name;
        this.color = color;
        this.location = location;
    }

    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting color
    public String getColor(){
        return this.color;
    }

    // setting color
    public void setColor(String color){
        this.color = color;
    }

    // getting location
    public String getLocation(){
        return this.location;
    }

    // setting location
    public void setLocation(String location){
        this.location = location;
    }

}
