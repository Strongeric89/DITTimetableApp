package com.eric.dt211c.dittimetableapp;


/**
 * Created by Eric on 19/03/2017.
 * This is the class for a task including the fields of
 * title,description, urgency level and date
 */
public class Task {

    private String title;
    private String description;
    private String urgencyLevel;
    private int value;
    private String today;
    private String time;
    public String image = "note";

    //    //date and time constructor
    public Task(String title, String description, String urgencyLevel, String date, String time) {
        this.title = title;
        this.description = description;
        this.urgencyLevel = urgencyLevel;

        this.image = "note";

        //getting the first character from the string in order to store its number
        char first = urgencyLevel.charAt(0);
        String c = "" + first;
        this.value = Integer.parseInt(c);



        this.today = date;
        this.time = time;
    }


    //getters and setters
    public String getDate() {

        return this.today;

    }

    public void setDate(String date) {

        this.today = date;

    }

    public String getTime() {


        return this.time;

    }

    public void setTime(String time) {


        this.time = time;

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

    public int getValue() {

        return this.value;
    }

    public void setUrgencyLevel(String urgencyLevel) {

        this.urgencyLevel = urgencyLevel;
    }

    public String toString() {
        return this.getTitle() + "-" + this.getDescription() + "-" + this.getUrgencyLevel();
    }//end toString


}//end task