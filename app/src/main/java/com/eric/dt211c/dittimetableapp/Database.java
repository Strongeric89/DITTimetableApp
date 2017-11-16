package com.eric.dt211c.dittimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created on 20/10/2017.
 * This code was is a Database class on SQLLite. using android built in SQLiteOpenHelper
 * All SQL Statements are taylored to suit the application
 */
public class Database extends SQLiteOpenHelper {
    //these are all constant fields needed throughout the database

    private static final String TABLE_NAME = "NOTES";
    private static final String COL_ID = "ID";
    private static final String COL_NOTE = "NOTE";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_PRIORITY = "PRIORITY";

    //date and time column
    private static final String COL_DATE = "DATE";
    private static final String COL_TIME = "TIME";

    //database constructor
    public Database(Context context) {
        super(context, "mynotes.db", null, 1);

    }//end constructor


    @Override
    public void onCreate(SQLiteDatabase db) {
        //oncreate is invoked when the apps activity is launched

        //create the database
        String sql = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT ,%s TEXT, %s TEXT, %s TEXT, %s TEXT);", TABLE_NAME, COL_ID, COL_TITLE, COL_NOTE, COL_PRIORITY, COL_DATE, COL_TIME);

        //execute the create table statement
        db.execSQL(sql);
        Log.d("eric", "data base has been created");

    }//end onCreate


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //this method is auto implemented


    }//end onUpgrade


    public void storeNotes(List<Task> notes) {
        //the following method will iterate through each task in the array list and add its contents
        //to the database. This is to persist the data

        SQLiteDatabase db = getWritableDatabase(); //get a writeable database reference

        db.delete(TABLE_NAME, null, null); // delete the old

        //the counter is in place to give each task a unique id in the database
        int i = 0;
        for (Task t : notes) {
            ContentValues v = new ContentValues();

            v.put(COL_ID, i);
            v.put(COL_TITLE, t.getTitle());
            v.put(COL_NOTE, t.getDescription());  //string
            v.put(COL_PRIORITY, t.getUrgencyLevel());
            v.put(COL_DATE, t.getDate());
            v.put(COL_TIME, t.getTime());

            db.insert(TABLE_NAME, null, v);

            i++;

        }//end for
        db.close(); // important to close the database
    }//end storeNotes


    public void deleteRow(String name) {
        //the following method will delete a row from the database, based on the note description.
        //this was decided as some notes may contain the same title

        SQLiteDatabase db = getReadableDatabase(); // get a readable database reference
        String sql = String.format("%s = '%s'", COL_NOTE, name);
        db.delete(TABLE_NAME, sql, null); // delete the row

    }

    public boolean update(String oldNote, String oldTitle, String oldPriority, Task t1) {
        //the following method will update a row within the database, based on the note description.
        //this was decided as some notes may contain the same title
        SQLiteDatabase db = getReadableDatabase();
        ContentValues v = new ContentValues();

        v.put(COL_NOTE, t1.getDescription());
        v.put(COL_TITLE, t1.getTitle());
        v.put(COL_PRIORITY, t1.getUrgencyLevel());
        String args[] = {oldNote};
        String args2[] = {oldTitle};
        String args3[] = {oldPriority};

        db.update(TABLE_NAME, v, COL_NOTE + "= ? ", args);
        db.update(TABLE_NAME, v, COL_TITLE + "= ? ", args);
        db.update(TABLE_NAME, v, COL_PRIORITY + "= ? ", args);


        return true;

    }


    public ArrayList<Task> getTasks() {
        //retrieving data from database
        ArrayList<Task> tasks = new ArrayList<Task>();

        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s ORDER BY %s", COL_ID, COL_TITLE, COL_NOTE, COL_PRIORITY, COL_DATE, COL_TIME, TABLE_NAME, COL_ID);

        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String title = c.getString(1);
            String description = c.getString(2);
            String priority = c.getString(3);
            String date = c.getString(4);
            String time = c.getString(5);


            //adding the query results to the arraylist
            tasks.add(new Task(title, description, priority, date, time));

        }//end while

        db.close();

        return tasks;

    }//end getTasks
}//end database
