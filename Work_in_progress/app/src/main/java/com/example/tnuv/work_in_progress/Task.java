package com.example.tnuv.work_in_progress;

import android.media.Image;

import java.util.Date;

/**
 * Created by CaptainBat on 23. 05. 2017.
 */

public class Task {

    //private variables
    private int id;
    private String name;
    private String description;
    private String video;
    private String image;
    private String created;
    private String deadline;

    // Empty constructor
    public Task(){

    }

    // constructor
    public Task(int id, String name, String description, String video, String image, String created, String deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.created = created;
        this.deadline = deadline;
    }

    // constructor
    public Task(String name, String description, String video, String image, String created, String deadline) {
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.created = created;
        this.deadline = deadline;
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

    // getting description
    public String getDescription(){
        return this.description;
    }

    // setting description
    public void setDescription(String description){
        this.description = description;
    }

    // getting video
    public String getVideo(){
        return this.video;
    }

    // setting video
    public void setVideo(String video){
        this.video = video;
    }

    // getting image
    public String getImage(){
        return this.image;
    }

    // setting image
    public void setImage(String image){
        this.image = image;
    }

    // getting category
    public String getCreated(){
        return this.created;
    }

    // setting category
    public void setCreated(String created){
        this.created = created;
    }

    // getting deadline
    public String getDeadline(){
        return this.deadline;
    }

    // setting deadline
    public void setDeadline(String deadline){
        this.deadline = deadline;
    }
}
