package com.eric.dt211c.dittimetableapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;

public class TimetableActivity extends AppCompatActivity {

    Button getFileContentsBtn= null;
    TextView fileContentsView = null;

    //location to where the txt file is saved to on your android device

    public String path = Environment.getExternalStorageDirectory() + "/DITTimetableApp/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        //get the contents from the file
        //NOTE: file must be named as timetable.txt

        getFileContentsBtn = (Button) findViewById(R.id.getFileContents);
        fileContentsView = (TextView) findViewById(R.id.fromFile);



        getFileContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //read file from within path

                try {
                    ReadApp reader = new ReadApp(path + "/timetable.txt");

                   String contentToDisplay =  reader.readFile();
                    Log.d("eric", contentToDisplay);

                    fileContentsView.setText(contentToDisplay);
                } catch (IOException e) {
                    //file not found
                    Log.d("eric", path + " : file not found ");
                }


            }

        });


    }
}
