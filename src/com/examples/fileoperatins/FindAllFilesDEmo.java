package com.examples.fileoperatins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FindAllFilesDEmo 

{
	public static void main(String args[])
	{

		
 
	System.out.println("Hello..");
	FindAllFilesDEmo obj= new FindAllFilesDEmo();
	//ArrayList<File> fList = null;
	//obj.listf("D:\\",fList);
	
		
		// ------------------New Code--------------------
		File currentDir = new File("."); // current directory
		displayDirectoryContents(currentDir);
	}

	public static void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
/*
	public void listf(String string, ArrayList<File> fList)
	{
		// TODO Auto-generated method stub

	    File directory = new File("D:\\");

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) 
	    {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	   
	    }
	    System.out.println(""+directory);
		
	}


	}
*/
	public static void listf(String directoryName, ArrayList<File> files)
	{
		    File directory = new File(directoryName);

		    // get all the files from a directory
		    File[] fList = directory.listFiles();
		    for (File file : fList) 
		    {
		        if (file.isFile()) {
		            files.add(file);
		        } else if (file.isDirectory()) {
		            listf(file.getAbsolutePath(), files);
		        }
		   
		    }
		    System.out.println(""+directory);
		}
} */
