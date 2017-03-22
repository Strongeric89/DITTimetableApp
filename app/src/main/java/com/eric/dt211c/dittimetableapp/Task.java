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

        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int mins = Calendar.getInstance().get(Calendar.MINUTE);

        this.time = hour + ":" + mins;
        this.today = day + "/" + (month + 1) + "/" + year ;


    }//end task Constructor

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
