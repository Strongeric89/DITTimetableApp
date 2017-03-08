package com.eric.dt211c.dittimetableapp;
/*read app designed by eric strong */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadApp
{
	public String filename;
	public static String[] lines = new String[10];

	public ReadApp(String filename) throws IOException
	{
		this.filename = filename;
		System.out.println("file: " + this.filename + " has been set up");

	}// end constructor

	public void readFile() throws IOException
	{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		int i = 0;

		while (in.hasNextLine())
		{
			String line = (i + 1) + ".\t" + in.nextLine();
			lines[i] = line;
			System.out.println(line);
			i++;
		}
		in.close();
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
