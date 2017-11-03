package com.eric.dt211c.dittimetableapp;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

/*

    The following class was created by eric strong
    This class is used to display the notes(task)

 */


public class ViewClassesActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private FragmentTransaction fragmentTransaction;
    public NavigationView navigationView;

    //list view
    public static ListView taskListView = null;
    public static TextView titleView = null;
    public static  ArrayAdapter<String> adapterMain = null;
    private ListView taskListViewMain = null;
    public static String path = Environment.getExternalStorageDirectory() + "/DITTimetableApp/timetable.txt";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);
        taskListViewMain = (ListView) findViewById(R.id.task_list);
        //nav drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //fragments
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new MondayFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Today's Classes");
        navigationView = (NavigationView) findViewById(R.id.naviagtion_view);


        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        day = day - 1;
        // convert the number to a string to return the correct version
        String dayOfWeek = new Integer(day).toString();
        String[] taskTitles = readFile(path,dayOfWeek);
        for(int i=0;i<taskTitles.length;i++){


                if(taskTitles[i].contains("_")){
                    taskTitles[i] = taskTitles[i].replace("_", " ");

            }


            String items[] = taskTitles[i].split(",");



            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
            taskTitles[i] = s;
            Log.d("Eric", s);
        }



        drawerLayout.openDrawer(Gravity.LEFT);

       adapterMain = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);//
        taskListViewMain.setAdapter(adapterMain);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(MenuItem item){

                switch(item.getItemId()){

                    case R.id.monday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Monday Classes");
                        item.setChecked(true);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView = (TextView) findViewById(R.id.titleDay);
                        titleView.setText("Monday");


                        String[] taskTitles = readFile(path,"1");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter);

                    }break;

                    case R.id.tuesday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Tuesdays Classes");
                        item.setChecked(true);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView = (TextView) findViewById(R.id.titleDay);
                        titleView.setText("Tuesday");

                        String[] taskTitles = readFile(path,"2");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter2);


                    }break;

                    case R.id.wednesday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Wednesdays Classes");
                        item.setChecked(true);

                        titleView = (TextView) findViewById(R.id.titleDay);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView.setText("Wednesday");

                        String[] taskTitles = readFile(path,"3");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter3);


                    }break;

                    case R.id.thursday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                       // fragmentTransaction.replace(R.id.main_container, new MondayFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Thursdays Classes");
                        item.setChecked(true);

                        titleView = (TextView) findViewById(R.id.titleDay);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView.setText("Thursday");

                        String[] taskTitles = readFile(path,"4");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter4);


                    }break;

                    case R.id.friday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Fridays Classes");
                        item.setChecked(true);

                        titleView = (TextView) findViewById(R.id.titleDay);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView.setText("Friday");

                        String[] taskTitles = readFile(path,"5");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter5);


                    }break;

                    case R.id.saturday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                       // fragmentTransaction.replace(R.id.main_container, new MondayFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Saturdays Classes");
                        item.setChecked(true);

                        titleView = (TextView) findViewById(R.id.titleDay);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView.setText("Saturday");

                        String[] taskTitles = readFile(path,"6");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter6);


                    }break;

                    case R.id.sunday:{
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Sundays Classes");
                        item.setChecked(true);

                        titleView = (TextView) findViewById(R.id.titleDay);
                        taskListView = (ListView) findViewById(R.id.task_list);
                        titleView.setText("Sunday");

                        String[] taskTitles = readFile(path,"7");
                        for(int i=0;i<taskTitles.length;i++){

                            if(taskTitles[i].contains("_")){
                                taskTitles[i] = taskTitles[i].replace("_", " ");

                            }

                            String items[] = taskTitles[i].split(",");

                            String s = items[1] + ":00 - " +  items[2] + " - " + items[3];
                            taskTitles[i] = s;
                            //Log.d("Eric", s);
                        }

                        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(ViewClassesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, taskTitles);
                        taskListView.setAdapter(adapter7);


                    }break;

                    default:{




                    }break;
                }

                return false;

            }


        });




    }//end on create

    public boolean onOptionsItemSelected(MenuItem menuitem){

        if(toggle.onOptionsItemSelected(menuitem)){
            return true;
        }
        return super.onOptionsItemSelected(menuitem);
    }


    public String[] readFile(String filename, String startswith) {
        String lines[] = new String [14];
        try{
            File file = new File(filename);
            Scanner in = new Scanner(file);

            int i = 0;

            while (in.hasNextLine()) {
                String line = "";

                line += in.nextLine() + "\n";

                if(line.startsWith(startswith)){
                    //Log.d("Eric", line );
                    lines[i] = line;
                    i++;
                }

            }

            in.close();
        }catch(FileNotFoundException fne){
            Log.d("Eric", "file not found" );
        }

        return lines;

    }// end readFile


}//end class