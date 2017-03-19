package com.eric.dt211c.dittimetableapp;


/*read app designed by eric strong */
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class ReadApp
{
    public static ArrayList<String[]> list = new ArrayList<String[]>();
    public static HashMap<Integer,String[]> classes = new HashMap <Integer,String[]>();
    public static HashMap<Integer, String[]> mapMonday = new HashMap<Integer, String[]>();
    public static HashMap<Integer, String[]> mapTuesday = new HashMap<Integer, String[]>();
    public static HashMap<Integer, String[]> mapWednesday = new HashMap<Integer, String[]>();
    public static HashMap<Integer, String[]> mapThursday = new HashMap<Integer, String[]>();
    public static HashMap<Integer, String[]> mapFriday = new HashMap<Integer, String[]>();

    public String filename;

    public ReadApp(String filename) throws IOException
    {
        this.filename = filename;

    }// end constructor

    public String readFile() throws IOException
    {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        String line = "";

        while (in.hasNextLine())
        {
            line += in.nextLine() + "\n";

        }

        in.close();
        return line;

    }// end readFile



    public HashMap<Integer,String[]> getContents() {

        File file = new File(filename);
        Scanner in;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        day = day - 1;

        // convert the number to a string to return the correct version
        String dayOfWeek = new Integer(day).toString();
        Log.d("eric", "day value is  " + dayOfWeek);


        try {
            in = new Scanner(file);
            String line;
            String[] lineContents;


            if (dayOfWeek.equalsIgnoreCase("1")) {

                while ((line = in.nextLine()) != null && line.startsWith("1")) {

                    lineContents = line.split(",");
                    Integer key = Integer.parseInt(lineContents[1]);
                    mapMonday.put(key, new String[]{lineContents[2], lineContents[3]});

                }//end for
                classes.putAll(mapMonday);
            }

            if (dayOfWeek.equalsIgnoreCase("2")) {

                while ((line = in.nextLine()) != null && line.startsWith("2")) {
                    lineContents = line.split(",");
                    Integer key = Integer.parseInt(lineContents[1]);
                    mapTuesday.put(key, new String[]{lineContents[2], lineContents[3]});

                }//end for
                classes.putAll(mapTuesday);
            }

            if (dayOfWeek.equalsIgnoreCase("3")) {

                while ((line = in.nextLine()) != null && line.startsWith("3")) {
                    lineContents = line.split(",");
                    Integer key = Integer.parseInt(lineContents[1]);
                    mapWednesday.put(key, new String[]{lineContents[2], lineContents[3]});

                }//end for
                classes.putAll(mapWednesday);
            }

            if (dayOfWeek.equalsIgnoreCase("4")) {

                while ((line = in.nextLine()) != null && line.startsWith("4")) {
                    lineContents = line.split(",");
                    Integer key = Integer.parseInt(lineContents[1]);
                    mapThursday.put(key, new String[]{lineContents[2], lineContents[3]});

                }//end for
                classes.putAll(mapThursday);
            }

            if (dayOfWeek.equalsIgnoreCase("5")) {

                while ((line = in.nextLine()) != null && line.startsWith("5")) {
                    lineContents = line.split(",");
                    Integer key = Integer.parseInt(lineContents[1]);
                    mapFriday.put(key, new String[]{lineContents[2], lineContents[3]});

                }//end for
                classes.putAll(mapFriday);

            }


//            while((line = in.nextLine()) !=null && line.startsWith(dayOfWeek)){
//                lineContents = line.split(",");
//                Integer key= Integer.parseInt(lineContents[1]);
//                classes.put(key,new String[] {lineContents[2],lineContents[3]});
        }
         catch (FileNotFoundException e)
        {
            System.out.println("file not found");
        }

        return classes;
    }



    public void writeFile(String outputfile, String argument) throws IOException
    {

        FileWriter fileout = new FileWriter(outputfile);
        PrintWriter text = new PrintWriter(fileout);

        text.close();

    }

}// end class