package com.eric.dt211c.dittimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


/**
 * Created on 20/03/2017.
 * This code was generated with the help of Cave of programming tutorial and Susan McKeevers Database class on SQLLite
 * with the exception of the actual SQL statements and delete statements
 */
public class Database extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "NOTES";
    private static final String COL_ID = "ID";
    private static final String COL_NOTE = "NOTE";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_PRIORITY = "PRIORITY";
    private static final String COL_DATE = "DATE";
    private static final String COL_TIME = "TIME";




    public Database(Context context){
        super(context,"mynotes.db",null, 1);



    }//end constructor


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the database
        String sql = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT ,%s TEXT, %s TEXT);",TABLE_NAME, COL_ID,COL_TITLE,COL_NOTE);

        db.execSQL(sql);

    }//end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//end onUpgrade

    public void storeNotes(List<Task> notes){
       SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        int i =0;
        for(Task t: notes){
            ContentValues v = new ContentValues();

            v.put(COL_ID,i);
            v.put(COL_TITLE,t.getTitle());
            v.put(COL_NOTE,t.getDescription());  //string
            v.put(COL_PRIORITY,t.getUrgencyLevel());


            db.insert(TABLE_NAME, null,v);

            i++;

        }//end for
        db.close();
    }//end storeNotes

    public void deleteRow(String name){

        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("%s = '%s'", COL_NOTE, name);
        db.delete(TABLE_NAME,sql,null);

    }


    //retrieving data from database
    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();

        SQLiteDatabase db = getReadableDatabase();



        String sql = String.format("SELECT %s,%s,%s,%s FROM %s ORDER BY %s",COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY,TABLE_NAME, COL_ID);

         Cursor c = db.rawQuery(sql,null);

        while(c.moveToNext()){
            int id = c.getInt(0);
            String title = c.getString(1);
            String description = c.getString(2);
            String priority = c.getString(3);


            //adding the query results to the arraylist
            tasks.add(new Task(title,description,priority));

        }//end while


        db.close();

        return tasks;

    }//end getTasks
}//end database
