package com.eric.dt211c.dittimetableapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class TimetableActivity extends AppCompatActivity {

    Button getFileContentsBtn= null;
    TextView fileContentsView = null;

    //location to where the txt file is saved to on your android device
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TimetableApp";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        //get the contents from the file
        //NOTE: file must be named as timetable.txt

        getFileContentsBtn = (Button) findViewById(R.id.getFileContents);
        fileContentsView = (TextView) findViewById(R.id.fromFile);

        //these lines set up the folder structure from within androids file manager... place your txt file in here
        //NOTE: call it timetable.txt. read spec on how the txt file should be formatted for correct reading.
        File dir = new File(path);
        dir.mkdirs();

        getFileContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }

        });


    }
}
