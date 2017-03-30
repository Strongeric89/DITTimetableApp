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
 * Created on 20/03/2017.
 * This code was generated with the help of Susan McKeevers Database class on SQLLite
 * with the exception of the actual SQL statements and delete statements
 */
public class Database extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "NOTES";
    private static final String COL_ID = "ID";
    private static final String COL_NOTE = "NOTE";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_PRIORITY = "PRIORITY";

    //date and time column
    private static final String COL_DATE = "DATE";
    private static final String COL_TIME = "TIME";


    public Database(Context context){
        super(context,"mynotes.db",null, 1);
        
    }//end constructor


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the database
        //String sql = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT ,%s TEXT, %s TEXT);",TABLE_NAME, COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY);

        //create table with date and time columns
        String sql = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT ,%s TEXT, %s TEXT, %s TEXT, %s TEXT);",TABLE_NAME, COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY,COL_DATE,COL_TIME);
        Log.d("eric",sql);

        Log.d("eric","data base has been created");
        db.execSQL(sql);

    }//end onCreate



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }//end onUpgrade

    //the following method will iterate through each task in the array list and add its contents
    //to the database. This is to persist the data
    public void storeNotes(List<Task> notes){
       SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        //the counter is in place to give each task a unique id in the database
        int i =0;
        for(Task t: notes){
            ContentValues v = new ContentValues();

            v.put(COL_ID,i);
            v.put(COL_TITLE,t.getTitle());
            v.put(COL_NOTE,t.getDescription());  //string
            v.put(COL_PRIORITY,t.getUrgencyLevel());

            //date and time cols
            v.put(COL_DATE,t.getDate());
            v.put(COL_TIME,t.getTime());


            db.insert(TABLE_NAME, null,v);

            i++;

        }//end for
        db.close();
    }//end storeNotes



    //the following method will delete a row from the database, based on the note description.
    //this was decided as some notes may contain the same title
    public void deleteRow(String name){


        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("%s = '%s'", COL_NOTE, name);
        db.delete(TABLE_NAME,sql,null);

    }




    //retrieving data from database
    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();


        SQLiteDatabase db = getReadableDatabase();


        //String sql = String.format("SELECT %s,%s,%s,%s FROM %s ORDER BY %s",COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY,TABLE_NAME, COL_ID);
        //date and time col
        String sql = String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s ORDER BY %s",COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY,COL_DATE,COL_TIME,TABLE_NAME,COL_ID);


        Cursor c = db.rawQuery(sql,null);

        while(c.moveToNext()){
            int id = c.getInt(0);
            String title = c.getString(1);
            String description = c.getString(2);
            String priority = c.getString(3);

            //date and time cols
            String date = c.getString(4);
            String time = c.getString(5);


            //adding the query results to the arraylist
            //tasks.add(new Task(title,description,priority));

            tasks.add(new Task(title,description,priority,date,time));

        }//end while


        db.close();

        return tasks;

    }//end getTasks
}//end database
