package com.eric.dt211c.dittimetableapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_notes);

        //creating the link to xml
        ListView taskListView =(ListView) findViewById(R.id.task_list);

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
                MyNotesActivity.taskList.remove(i);
                recreate();
                return false;
            }
        });



    }//end on create

    public void FormActivity (View view){
        Intent intent = new Intent(this,ViewNotesActivity.class);
        createDialogIntent(this, intent);
    }


    //for additional info on the charities
    public static void createDialogIntent(final Context context, final Intent intent) {

        String title;
        String desription;
        String priority;



        new AlertDialog.Builder(context).setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setIcon(R.drawable.classroom3)
                .setPositiveButton(R.string.alert_agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //this is what happens when Accept is clicked
                        //context.startActivity(intent);
                    }
                }).show();

    }






}//end class
