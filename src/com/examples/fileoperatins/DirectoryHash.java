	package com.examples.fileoperatins;
	
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.IOException;
	import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;
	
	public class DirectoryHash
	{
	static String Fpath;
		public static void main(String[] args) throws IOException, NoSuchAlgorithmException
		{
		
			File[] paths;
			FileSystemView fsv = FileSystemView.getFileSystemView();

			// returns pathnames for files and directory
			paths = File.listRoots();
			File[] roots = File.listRoots();
			System.out.println("\t\n List Of Drives...");
			for(int i =1; i < roots.length-2 ; i++)
			    System.out.println("\tRoot["+i+"]:" + roots[i]);
			
			
			System.out.println("Enter Directory Name:=");
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
	//		String DrName = b.readLine();
			
		//	DirectoryHash h=new DirectoryHash();
	/*		for(int i=3;i<roots.length-2;i++)
			{
				// Fpath=roots.get(i);
				 String Rpath;
				Rpath=roots[i].getPath();
				List<String> list =h.displayFiles(Rpath);
			} */
			
			
			
			List<String> list;
			DirectoryHash h=new DirectoryHash();
			String Rpath = b.readLine();
			list =h.displayFiles(Rpath);
			for(int i=0;i<list.size();i++)
			{
				 Fpath=list.get(i);
			//System.out.println(" "+Fpath);
			//	list.add(Fpath) ;
			
			}
		}
		List<String> displayFiles(String Rpath) throws IOException , NoSuchAlgorithmException
		 {
			 File file= new File(Rpath);
			 File[] listOfFiles = file.listFiles();
			 
			 List<String> list = new ArrayList<String>();
			 
			 if(listOfFiles != null)
	         {
			 
			System.out.println("Files location :"+file);
			System.out.println("files length="+listOfFiles.length);			
			
			
            
			for(int i=0;i<listOfFiles.length;i++)
		 
		        {
					if(listOfFiles[i].isFile())
					{
					  String HoldPath=listOfFiles[i].getPath();		
					  list.add(""+HoldPath);
					  System.out.println(""+listOfFiles[i].getPath());
					}	 
					
					else if(listOfFiles[i].isDirectory())

					{
						System.out.println(listOfFiles[i].getName());
						String subfolder=listOfFiles[i].getPath();
						List<String> tempList;
 						tempList=displayFiles(subfolder);
						list.addAll(tempList);
						System.out.println(listOfFiles[i].getPath());
					}
				}
            }
			 
			System.out.println("Drives Data:"+list);
	    	return list;
		 }
		
	
	}
