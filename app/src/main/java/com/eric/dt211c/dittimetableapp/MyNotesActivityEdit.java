package com.eric.dt211c.dittimetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/*
* The following class was created by eric strong
* This page will create a note (task class) that will be added to the arraylist
* The idea is to order them by priority and to persist them for later use.
* */

public class MyNotesActivityEdit extends AppCompatActivity {

    public static ArrayList<Task> taskList = new ArrayList<>();

    private EditText mTitleField = null;
    private EditText mDescField = null;
    private Spinner mUgencyField = null;

    public static String oldNote = null;
    public static String oldTitle = null;
    public static String oldPriority = null;

    //database
    private Database db = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String []items = bundle.getStringArray(ViewNotesActivity.TASK_KEY);

        //Identifing the Id's from the activity_form xml
        mTitleField = (EditText) findViewById(R.id.TaskNameField2);
        mDescField = (EditText) findViewById(R.id.TaskDesField2);
        mUgencyField = (Spinner) findViewById(R.id.TaskSpinner);

        mTitleField.setText(items[0], TextView.BufferType.EDITABLE);
        mDescField.setText(items[1], TextView.BufferType.EDITABLE);

        oldNote = items[1];
        oldTitle = items[0];
        oldPriority = items[2];



    }//end onCreate


    //when the submit button is clicked the fields will be taken from the editviews and
    //plugged into the task constructor. and then stored in the array list.
    public void onSubmitPressed(View view) {


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

        String time = hour + ":" + mins;
        String date = day + "/" + (month + 1) + "/" + year;


        // creating task
        Task task = new Task(title.toUpperCase(), description, urgency, date, time);

        //update db
        boolean flag = db.update(oldNote, oldTitle, oldPriority,task);


        //a toast to notify user task was created and a counter is displayed
        Toast.makeText(this, "Note updated ",
                Toast.LENGTH_LONG).show();



        //clear each field for next note
        mTitleField.setText("");
        mDescField.setText("");
        mUgencyField.setSelection(0);

        if(flag == true){
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
        }



    }//end on submit pressed

}//end class