package com.eric.dt211c.dittimetableapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ViewNotesActivity extends AppCompatActivity {


    //for displayinformation class
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    //temp variables
    public static String[] info = new String[2];
    public static String urgency1 = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        //creating the link to xml
        ListView taskListView =(ListView) findViewById(R.id.task_list);
        taskListView.setClickable(true);

        //sort arrayList
        Collections.sort(MyNotesActivity.taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int comparing = t1.getValue() - t2.getValue();
                return comparing;
            }
        });

        String [] taskTitles = new String[MyNotesActivity.taskList.size()];  //goes to the MyNotesActivity,java and gets the size of the array list
        //populate the array

        for(int i=0;i<taskTitles.length;i++){

            taskTitles[i]=MyNotesActivity.taskList.get(i).getTitle(); //getting the title part from the list of index i

        }//end for



        //setting up arrayAdapter whichi handles the contents of the ListView
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);

        taskListView.setAdapter(adapter);

        //Implementing the listener for pressing on a long click on an item
        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                String toberemoved = MyNotesActivity.taskList.get(i).getTitle();
                MyNotesActivity.taskList.remove(i);

                //make phone vibrate
                Vibrator v = (Vibrator) ViewNotesActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);


                Toast.makeText(ViewNotesActivity.this,toberemoved + " Note Removed",
                        Toast.LENGTH_LONG).show();
                recreate();
                return false;
            }
        });



        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int i, long l){
                String title = null;
                String description = null;
                String urgency = null;
                int value;

                title =MyNotesActivity.taskList.get(i).getTitle();
                description =MyNotesActivity.taskList.get(i).getDescription();
                urgency = MyNotesActivity.taskList.get(i).getUrgencyLevel();
                value = MyNotesActivity.taskList.get(i).getValue();

                info[0] = title;
                info[1] = description;
                urgency1 = urgency;

                AlertDialog.Builder adb = new AlertDialog.Builder(ViewNotesActivity.this);

                adb.setTitle(getString(R.string.titleLabel)+ " " + info[0]);

                adb.setMessage(getString(R.string.descriptionLabel)+ " " + info[0] + "\n" + getString(R.string.priorityLabel)+ " " + urgency1 + " \n" + value );
                adb.setPositiveButton("OK", null);
                adb.setIcon(R.drawable.classroom3);
                adb.show();


            }//onItemClick

        });

    }//end on create

}//end class
