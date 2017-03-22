package com.eric.dt211c.dittimetableapp;

import java.util.*;

/**
 * Created by Eric on 19/03/2017.
 */
public class Task {

    private String title;
    private String description;
    private String urgencyLevel;
    private int value;
    private String today;
    private String time;

    public Task(String title, String description, String urgencyLevel){
        this.title = title;
        this.description = description;
        this.urgencyLevel = urgencyLevel;

        char first = urgencyLevel.charAt(0);
        String c = "" + first;
        this.value = Integer.parseInt(c);



    }//end task Constructor

    //overloaded constructor
    public Task(String title, String description, String urgencyLevel, String date, String time){
        this.title = title;
        this.description = description;
        this.urgencyLevel = urgencyLevel;

        char first = urgencyLevel.charAt(0);
        String c = "" + first;
        this.value = Integer.parseInt(c);

       this.today = date;
        this.time = time;



    }

    public String getDate(){


        return this.today;

    }

    public String getTime(){


        return this.time;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public int getValue(){
        return this.value;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }


    public String toString(){
        return  this.getTitle() + "-" + this.getDescription() + "-" + this.getUrgencyLevel();
    }//end toString


}//end task
