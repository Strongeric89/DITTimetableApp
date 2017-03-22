package com.eric.dt211c.dittimetableapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


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
        mTitleField = (EditText)findViewById(R.id.TaskNameField2);
        mDescField = (EditText) findViewById(R.id.TaskDesField2);
        mUgencyField = (Spinner) findViewById(R.id.TaskSpinner);

    }//end onCreate


    public void onSubmitPressed(View view){


        //for creating date
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int mins = Calendar.getInstance().get(Calendar.MINUTE);

        String time = hour + ":" + mins;
        String today = day + "/" + (month + 1) + "/" + year ;

        String title = mTitleField.getText().toString();
        String description = mDescField.getText().toString();
        String urgency = mUgencyField.getSelectedItem().toString();


        if(title.equals("") || description.equals("")){
            Toast.makeText(this, "Please input all information",Toast.LENGTH_LONG).show();

            return;
        }// end if


        //this is overloaded due to the fact that the database recreates when viewNOtes activity.
        //there for the time would be incorrect every time
        Task task = new Task(title.toUpperCase(), description,urgency, today,time);




       //add task to arrayList
        taskList.add(task);



        Toast.makeText(this,"Note Added: " + taskList.size(),
                Toast.LENGTH_LONG).show();

        db.storeNotes(taskList);




        //clear the screen
        mTitleField.setText("");
        mDescField.setText("");
        mUgencyField.setSelection(0);





    }//end on submit pressed






}//end class
