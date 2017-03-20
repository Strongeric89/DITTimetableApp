package com.eric.dt211c.dittimetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Eric on 19/03/2017.
 */
public class DisplayInformation extends AppCompatActivity {

    //this is the information display when the user clicks on their note. an intent is send to the
    //next page with these descriptions

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_information);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    String title = bundle.getString(ViewNotesActivity.TITLE);
    String description = bundle.getString(ViewNotesActivity.DESCRIPTION);



    TextView title1 = (TextView) findViewById(R.id.titleDisplay);
    title1.setText(title);

    TextView description1 = (TextView) findViewById(R.id.descriptionDisplay);
    description1.setText(description);

}//end onCreate


}//end class
