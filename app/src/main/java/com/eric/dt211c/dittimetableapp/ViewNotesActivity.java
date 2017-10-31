package com.eric.dt211c.dittimetableapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;

/*

    The following class was created by eric strong
    This class is used to display the notes(task)

 */
public class ViewNotesActivity extends AppCompatActivity {

    //database
    private Database db = new Database(this);


    //for displayinformation class
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public static final String TASK_KEY = "TASK";

    //temp variables
    public static String[] info = new String[2];
    public static String urgency1 = null;
    public static int index = 0;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        //creating the link to xml
        ListView taskListView = (ListView) findViewById(R.id.task_list);
        taskListView.setClickable(true);

        Task list[] = new Task[MyNotesActivity.taskList.size()];

        //for debug reasons to ensure the database has recieved good data
        for (Task t : MyNotesActivity.taskList) {

            Log.d("Eric", String.format("got task: (\n%s, \n%s, \n%s)", t.getTitle(), t.getDescription(), t.getUrgencyLevel()));
        }


        //sort arrayList
        //Soring the arraylist by the tasks attribute of value (int)
        Collections.sort(MyNotesActivity.taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int comparing = t1.getValue() - t2.getValue();
                return comparing;
            }
        });

        //displaying the titles in the listview component
        String[] taskTitles = new String[MyNotesActivity.taskList.size()];  //goes to the MyNotesActivity,java and gets the size of the array list
        //populate the array

        for (int i = 0; i < taskTitles.length; i++) {

            list[i] = MyNotesActivity.taskList.get(i);

            taskTitles[i] = MyNotesActivity.taskList.get(i).getTitle(); //getting the title part from the list of index i

        }//end for


        //setting up arrayAdapter which handles the contents of the ListView
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);

        NoteAdapter adapter = new NoteAdapter(getApplicationContext(), R.layout.row, list);

        if(taskListView != null){
            taskListView.setAdapter(adapter);

        }
        //taskListView.setAdapter(adapter);

        //Implementing the listener for pressing on a long click on an item
        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                String toberemoved = MyNotesActivity.taskList.get(i).getTitle();
                String rowtoberemove = MyNotesActivity.taskList.get(i).getDescription();

                //if long click on list view item it is removed from the database also
                db.deleteRow(rowtoberemove);

                //if long click on list view item it is removed from the arraylist
                MyNotesActivity.taskList.remove(i);

                //make phone vibrate
                Vibrator v = (Vibrator) ViewNotesActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);

                //toast notification to tell user not has been removed
                Toast.makeText(ViewNotesActivity.this, toberemoved + " Note Removed",
                        Toast.LENGTH_LONG).show();
                recreate();
                return false;
            }
        });


        //when list view item is clicked an alert intent is called to display contents of the tasks stored
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String title = null;
                String description = null;
                String urgency = null;


                //getting the attributes from the arraylist
                title = MyNotesActivity.taskList.get(i).getTitle();
                description = MyNotesActivity.taskList.get(i).getDescription();
                urgency = MyNotesActivity.taskList.get(i).getUrgencyLevel();

                //to display in the alert
                info[0] = title;
                info[1] = description;
                urgency1 = urgency;
                index = i;

                AlertDialog.Builder alert = new AlertDialog.Builder(ViewNotesActivity.this);

                alert.setTitle(getString(R.string.titleLabel) + " " + info[0]);

                alert.setMessage(getString(R.string.descriptionLabel) + " " + info[1] + "\n" + getString(R.string.priorityLabel) + " " + urgency1 + " \nDate Created: " + MyNotesActivity.taskList.get(i).getDate() + "\nTime Created: " + MyNotesActivity.taskList.get(i).getTime());
                alert.setPositiveButton("OK", null);
                alert.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ViewNotesActivity.this, MyNotesActivityEdit.class);

                        String urg = urgency1.charAt(0) + "";
                        String items[] = {info[0],info[1], urg};
                        Bundle bundle = new Bundle();
                        bundle.putStringArray(TASK_KEY,items);

                        intent.putExtras(bundle);
                        Log.d("Eric", "items:" + items[0] + " " + items[1] + " " + items[2]);


                        startActivity(intent);
                    }
                });



               // alert.setNegativeButton("Edit", null); // will call an intent over to edit
                alert.setIcon(R.drawable.classroom3);
                alert.show();



            }//onItemClick

        });

    }//end on create

}//end class