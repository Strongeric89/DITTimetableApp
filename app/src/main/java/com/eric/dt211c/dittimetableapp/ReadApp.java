package com.eric.dt211c.dittimetableapp;


/*
    Read app designed by eric strong
    The following class was created for a java lab.
    This class reads in content from the text file and does various file manipulation in order to achieve
    the apps results



 */

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class ReadApp {
    //hash map is used for the key:value pair (time:detail)
    public static HashMap<Integer, String[]> classes = new HashMap<Integer, String[]>();
    public static ArrayList<String> list = new ArrayList<String>();

    public String filename;


    //points to the file
    public ReadApp(String filename) throws IOException {

        this.filename = filename;

    }// end constructor

    //method to read in file to memory
    public String readFile() throws IOException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        String line = "";

        while (in.hasNextLine()) {
            line += in.nextLine() + "\n";

        }

        in.close();
        return line;

    }// end readFile


    //The following method reads in the file and uses the hashmap structure to store the details in order
    //to pass over to other classes

    //Once the day of the week has been identified (1-7) a switch case will choose which structure to populate and
    //which portion of the file to read in. This is so the entire file is not used in memory
    public HashMap<Integer, String[]> getContents() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        day = day - 1;
        // convert the number to a string to return the correct version
        String dayOfWeek = new Integer(day).toString();
        switch (day) {

            case 1: {
                sorter(dayOfWeek);

            }
            break;

            case 2: {
                sorter(dayOfWeek);

            }
            break;

            case 3: {
                sorter(dayOfWeek);

            }
            break;

            case 4: {
                sorter(dayOfWeek);

            }
            break;

            case 5: {
                sorter(dayOfWeek);

            }
            break;

            case 6: {
                sorter(dayOfWeek);

            }
            break;

            case 7: {
                sorter(dayOfWeek);

            }
            break;

        }// end switch

        return classes;
    }// end getContents


    //The sorter method is used to then take out of the file only the day of the week that is needed
    //and put into a structure. this structure is then used in other classes
    public void sorter(String dayOfWeek) {

        File file = new File(filename);

        Scanner in;
        try {
            in = new Scanner(file);

            String line;
            while (in.hasNext()) {
                line = in.next();

                //if the line starts with the day of the week we need read in to the string
                if (line.startsWith(dayOfWeek)) {
                    // added to arraylist
                    list.add(line);

                } // end getting lines

            } // reading in the file

            String values[] = new String[4];

            for (String s : list) {
                //each line is iterated and split by a coma
                values = s.split(",");
                for (int j = 0; j < values.length; j++) {
                    //because each line is seperated at the coma it creates different strings
                    //which are then put into an array of strings followed by an key to identify the day

                    // IMPORTANT PART TO POPULATE THE HASHMAP
                    Integer key = Integer.parseInt(values[1]);
                    classes.put(key, new String[]
                            {values[2], values[3]});

                } // end inner

            } // end enhanced for

        } catch (FileNotFoundException e) {

            Log.d("eric", "file not found");
        }

    }// end sorter

}// end class