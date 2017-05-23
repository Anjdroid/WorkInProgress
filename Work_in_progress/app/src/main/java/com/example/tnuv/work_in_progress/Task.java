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
    private Image image;
    private Date created;
    private Date deadline;

    // Empty constructor
    public Task(){

    }

    // constructor
    public Task(int id, String name, String description, String video, Image image, Date created, Date deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.created = created;
        this.deadline = deadline;
    }

    // constructor
    public Task(String name, String description, String video, Image image, Date created, Date deadline) {
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
    public Image getImage(){
        return this.image;
    }

    // setting image
    public void setImage(Image image){
        this.image = image;
    }

    // getting category
    public Date getCreated(){
        return this.created;
    }

    // setting category
    public void setCreated(Date created){
        this.created = created;
    }

    // getting deadline
    public Date getDeadline(){
        return this.deadline;
    }

    // setting deadline
    public void setDeadline(Date deadline){
        this.deadline = deadline;
    }
}
