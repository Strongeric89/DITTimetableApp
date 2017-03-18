package com.eric.dt211c.dittimetableapp;
/*read app designed by eric strong */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



/*
 String fileText = ""; //empty string for content from the file

    InputStream is = getAssets().open("timetable.txt");
    int size = is.available();
    byte[] buffer = new byte[size];
    is.read();
    is.close();
    fileText = new String(buffer);

 */
public class ReadApp
{
	public String filename;
	public static String[] lines = new String[10];

	public ReadApp(String filename) throws IOException
	{
		this.filename = filename;
		//System.out.println("file: " + this.filename + " has been set up");

	}// end constructor

	public String readFile() throws IOException
	{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		int i = 0;
		String line = "";

		while (in.hasNextLine())
		{
			line += in.nextLine() + "\n";

		}


		in.close();
		return line;

	}// end readFile

	public void writeFile(String outputfile, String argument) throws IOException
	{
		// writing to a file

		// // saves file to the parent directory of the project - f5 to refresh
		// Files.write(Paths.get(output), argument.getBytes());

		FileWriter fileout = new FileWriter(outputfile);
		PrintWriter text = new PrintWriter(fileout);
		System.out.println("writing to file now.....");
		text.println(argument);
		text.close();

	}
}// end class
