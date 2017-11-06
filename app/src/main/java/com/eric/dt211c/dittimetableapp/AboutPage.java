package com.eric.dt211c.dittimetableapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
    The folllowing code was created by eric strong. This is the about page that is useful for when
    the app is first launched. There will be a link to email the developer about any questions
    and also a link to watch the video on how to format the mytimetable.txt file
    There will be instructions on how to use the app included also in this page


 */

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //list adapter
        String about[] = { "STEP 1\n\nGo to Settings>App>MY NEXT CLASS>Permissions> Allow Access to Storage.\n",
                "STEP 2\n\nLocate on your internal storage, DITTimetableApp>timetable.txt. Open the file and edit your schedule.\n",
                "STEP 3\n\nInstructions to format the timetable.txt\n" +
                        "\n1. COMA , all fields must be separated by a coma\n" +
                        "\n2. Underscore _ for words such as web development ensure to leave no white space and use the underscore like so web_development\n" +
                        "\n3. No full stops, spaces or any other punctuation.\n" +
                        "\nIt is important to follow these structure rules for timetable.txt file.Included is a template to use so you can just edit the fields to match your timetable.\n",
                        "SAMPLE ENTRY:\n1,9,free,no_class,\n" +
                                "\nIf you do not have a class a specific hour please enter No_Class and also for the room class room specify No_Class\n",
                        "Disclaimer\n\nThank you for downloading this app. It is my first app and hope it has some use. Please forgive any mistakes and email me if you find any.\n",

                "About this App\n\nI made this app as I always found myself between classes having to zoom in on a screenshot of my timetable. It can be a pain " +
                        "so I thought id develop a usefull app that within a click of a button you can find out where you are supposed to be. The app is controlled by the student or lecturer." +
                        "You put in your schedule via the timetable.txt document once a year and then change it the following year.\n\n"
        };



        ListView taskListView = (ListView) findViewById(R.id.task_list_about);

        //array of my favorite movies
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, about);

        taskListView.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            //put in my email here
            public void onClick(View view) {
                sendContact();

            }
        });

    }

    //The following code will create an intent to create an email.
    public void sendContact() {
        String[] targetEmail = new String[]{"strong.erik@gmail.com"};
        String mSubject = "Enquiry about the Timetable App";
        String mMessage = "FAO: Eric,";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, targetEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
        intent.putExtra(Intent.EXTRA_TEXT, mMessage);

        try {
            startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (android.content.ActivityNotFoundException ex) {

            //toast action to appear if there are no email clients installed for a long period of time
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }//end sendContact

    //the following code will create an intent to the browser to go to the said website
    public void onurlClick(View view) {
        goToUrl("https://youtu.be/WJB7E7igK1o");
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}//end class
