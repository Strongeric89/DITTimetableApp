package com.eric.dt211c.dittimetableapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 20/03/2017.
 */
public class Database extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "NOTES";
    private static final String COL_ID = "ID";
    private static final String COL_NOTE = "NOTE";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_PRIORITY = "PRIORITY";



    public Database(Context context){
        super(context,"mynotes.db",null, 1);



    }//end constructor


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the database
        String sql = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT ,%s TEXT, %s INTEGER)",TABLE_NAME, COL_ID,COL_TITLE,COL_NOTE,COL_PRIORITY);

        db.execSQL(sql);

    }//end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//end onUpgrade

    public void storeNotes(ArrayList<Task> notes){
       SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        for(Task t: notes){
            ContentValues v = new ContentValues();
            int i=0;
            v.put(COL_ID,i);
            v.put(COL_NOTE,t.getDescription());  //string
            //v.put(COL_PRIORITY,t.getUrgencyLevel()); //int
            v.put(COL_TITLE,t.getTitle());

            db.insert(TABLE_NAME, null,v);

            i++;

        }//end for
        db.close();
    }//end storeNotes
}//end database
