package com.eric.dt211c.dittimetableapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*

    The following class was created by eric strong
    This class is used to display the notes(task)

 */


public class ViewClassesActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {


        //to ensure the txt file is in the correct location
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);

        //creating the link to xml
        ListView taskListView = (ListView) findViewById(R.id.task_list);


        //displaying the titles in the listview component
        String[] taskTitles = new String[14];  //goes to the ReadApp.java and gets the size of the array lis


        //go through the structure and get only the string values back

//        Log.d("eric", "size of Classes is" + PopulateTimetable.classes.size());
//        Log.d("eric", "Classes is empty" + PopulateTimetable.classes.isEmpty());

        //for debugging reasons
//        for(int i =0; i<PopulateTimetable.classes.size(); i++){
//            Log.d("eric", "" + PopulateTimetable.classes.keySet());
//
//        }


        int j = 0;
        for (int i = 8; i < 22; i++) {

            String s = i + ":00  - " + PopulateTimetable.classes.get(i)[0] + " - " + PopulateTimetable.classes.get(i)[1];

            if(s.contains("_")){
                s = s.replace('_', ' ');
            }

            taskTitles[j] = s;

            j++;

        }//end for


        //setting up arrayAdapter which handles the contents of the ListView
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);

        taskListView.setAdapter(adapter);


    }//end on create

}//end class