package com.eric.dt211c.dittimetableapp;

/*
*
* Author: Eric Strong DT211C/2
* Module: OOP programming
* Description: The DIT timetable app will read from a file or database and will indicate to the
* student current class, next class and times of the next class. Some other features will be included
*
 */

//use this to debug
//Log.d("eric", "message");
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Environment;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button timetableBtn = null;
    private Button viewNotesBtn = null;
    private Button myNotesBtn = null;
    public boolean running = true;
    public static int numberOfStarts = 0;
    public static final String STARTS = "number of starts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This will create the directory structure within the phone - thanks to help from stack overflow


        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "DITTimetableApp");
        Log.d("eric", "create directory in " + Environment.getExternalStorageDirectory().getAbsolutePath() );

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("eric", "failed to create directory");
            }
        }

        //popup when the app is launched.
        //Shared preferences will ensure that this is only run on the first 3 times the application is launched

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        int number = 0;
        numberOfStarts = sp.getInt(STARTS,number);

        if(numberOfStarts <= 3 || running == true){

            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

            adb.setTitle("Welcome to My next Class App");

            adb.setMessage("Thank you for downloading. Please ensure that you have put your txt file in DITTimetableApp directory. Would you like" +
                    " to view a video on how to format this file?");

            adb.setNegativeButton("No", null);


            //this will create an intent to the about page
            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    running = false;
                    //this is what happens when YES is clicked
                    startActivity(new Intent(MainActivity.this,AboutPage.class));

                }
            });
            adb.setIcon(R.drawable.clock1);
            adb.show();

            numberOfStarts ++;


        }//end running

        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(STARTS,numberOfStarts);
        ed.commit();




        //switching to timetable screen and activity
        timetableBtn = (Button) findViewById(R.id.goToTimetable);

        timetableBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(),PopulateTimetable.class);

                //checks if the file is in the directory before opening up timetable intent
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DITTimetableApp/timetable.txt";
                File fileCheck = new File(filePath);
                if(fileCheck.exists()){
                    startActivity(i);
                }//end file checker

                else{
                    Toast.makeText(MainActivity.this, "Please ensure timetable.txt is in DITTimetableApp directory",
                            Toast.LENGTH_LONG).show();
                }



            }
        });

        //switching to MyNotes screen and activity
        myNotesBtn = (Button) findViewById(R.id.goToMyNotes);

        myNotesBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(),MyNotesActivity.class);
                startActivity(i);
            }
        });

        //switching to ViewNotes screen and activity
        viewNotesBtn = (Button) findViewById(R.id.goToViewNotes);

        viewNotesBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(),ViewNotesActivity.class);
                startActivity(i);
            }
        });




    }//end main onCreate
}//end class MainActivity
