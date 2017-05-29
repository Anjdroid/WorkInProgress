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
    private int done;

    // Empty constructor
    public Task(){

    }

    // constructor
    public Task(int id, String name, String description, String video, String image, String created, String deadline, int done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.created = created;
        this.deadline = deadline;
        this.done = done;
    }

    // constructor
    public Task(String name, String description, String video, String image, String deadline) {
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
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

    // getting done
    public int getDone(){
        return this.done;
    }

    // setting done
    public void setDone(int done){
        this.done = done;
    }
}
