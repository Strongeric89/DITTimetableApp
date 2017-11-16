package com.eric.dt211c.dittimetableapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
* The following class was created by eric strong
* This page will create a note (task class) that will be added to the arraylist
* The idea is to order them by priority and to persist them for later use.
* */

public class MyNotesActivity extends AppCompatActivity {

    public static ArrayList<Task> taskList = new ArrayList<>();

    private EditText mTitleField;
    private EditText mDescField;
    private Spinner mUgencyField;

    //database
    private Database db = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        //Identifing the Id's from the activity_form xml
        mTitleField = (EditText) findViewById(R.id.TaskNameField2);
        mDescField = (EditText) findViewById(R.id.TaskDesField2);
        mUgencyField = (Spinner) findViewById(R.id.TaskSpinner);

    }//end onCreate



    public void onSubmitPressed(View view) {
        //when the submit button is clicked the fields will be taken from the editviews and
        //plugged into the task constructor. and then stored in the array list.

        String title = mTitleField.getText().toString();
        String description = mDescField.getText().toString();
        String urgency = mUgencyField.getSelectedItem().toString();


        //to ensure that all fields will be populated.
        if (title.equals("") || description.equals("")) {
            Toast.makeText(this, "Please input all information", Toast.LENGTH_LONG).show();

            return;
        }// end if

        //get date and time fields
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int mins = Calendar.getInstance().get(Calendar.MINUTE);
        int sec = Calendar.getInstance().get(Calendar.SECOND);

        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String time = sdfDate.format(now);

        //String time = hour + ":" + mins + ":" + sec;
        String date = day + "/" + (month + 1) + "/" + year;

        // creating task
        Task task = new Task(title.toUpperCase(), description, urgency, date, time);

        //add task to arrayList
        taskList.add(task);

        //a toast to notify user task was created and a counter is displayed
        Toast.makeText(this, "Note Added: " + taskList.size(),
                Toast.LENGTH_LONG).show();

        //notes are also stored in the database
        db.storeNotes(taskList);

        //clear each field for next note
        mTitleField.setText("");
        mDescField.setText("");
        mUgencyField.setSelection(0);

    }//end on submit pressed

}//end class