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
    public static HashMap<Integer, String[]> classes = new HashMap<Integer, String[]>();
    public static ArrayList<String> list = new ArrayList<String>();

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

    public HashMap<Integer, String[]> getContents()
    {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        day = day - 1;
        // convert the number to a string to return the correct version
        String dayOfWeek = new Integer(day).toString();
        switch (day)
        {

            case 1:
            {
                sorter(dayOfWeek);

            }
            break;

            case 2:
            {
                sorter(dayOfWeek);

            }
            break;

            case 3:
            {
                sorter(dayOfWeek);

            }
            break;

            case 4:
            {
                sorter(dayOfWeek);

            }
            break;

            case 5:
            {
                sorter(dayOfWeek);

            }
            break;

            case 6:
            {
                sorter(dayOfWeek);

            }
            break;

            case 7:
            {
                sorter(dayOfWeek);

            }
            break;

        }// end switch

        return classes;
    }// end getContents

    public void sorter(String dayOfWeek)
    {

        File file = new File(filename);

        Scanner in;
        try
        {
            in = new Scanner(file);

            String line;
            while (in.hasNext())
            {
                line = in.next();
                if (line.startsWith(dayOfWeek))
                {
                    // added to arraylist
                    list.add(line);

                } // end getting lines

            } // reading in the file

            String values[] = new String[4];

            for (String s : list)
            {
                values = s.split(",");
                for (int j = 0; j < values.length; j++)
                {
                    // IMPORTANT PART TO POPULATE THE HASHMAP
                    Integer key = Integer.parseInt(values[1]);
                    classes.put(key, new String[]
                            { values[2], values[3] });

                } // end inner

            } // end enhanced for

        } catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }

    }// end sorter

    public void writeFile(String outputfile, String argument) throws IOException
    {

        FileWriter fileout = new FileWriter(outputfile);
        PrintWriter text = new PrintWriter(fileout);

        text.close();

    }

}// end class