package com.eric.dt211c.dittimetableapp;

/*
*
* Author: Eric Strong DT211C/2
* Module: OOP programming
* Description: The DIT timetable app will read from a file or database and will indicate to the
* student current class, next class and times of the next class. Some other features will be included
*
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    //creating instance of ReadApp


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            ReadApp reader = new ReadApp("test.txt");
            System.out.println(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//end main onCreate
}//end class MainActivity
