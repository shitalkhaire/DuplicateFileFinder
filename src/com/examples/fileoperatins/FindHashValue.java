package com.examples.fileoperatins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class FindHashValue
{

	static	String rootPath;
	static  List<String> list = new ArrayList<String>();
	
	public static void main(String[] args) throws  NoSuchAlgorithmException, SAXException, ParserConfigurationException, IOException, SQLException
	{
		int iChoice;
		do {
		System.out.println("Choose Operation what you want:=");
		System.out.println("1. Scan all drives");
		System.out.println("2. Show duplicate");
		System.out.println("3. Quit");
		BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
		
		iChoice=Integer.parseInt(b.readLine());
		
		switch(iChoice)
		{
	
		case 1:
				// returns list of Drives 
				File[] roots = File.listRoots();
				System.out.println("\t\n List Of Drives...");
				for(int i =1; i < roots.length-1 ; i++) 
				
				System.out.println("\tRoot["+i+"]:" + roots[i]);
				configurationClass obj = new configurationClass ("Config.xml");
				
				 // object is Created of configurationClass
				/*	configurationClass obj = new configurationClass ("Config.xml");
				ArrayList<String> LExclude=obj.getExcludeFolders();
				//System.out.println(""+ LExclude);   */
		
				ArrayList<String> driveList = obj.getDrives();
				 
				ArrayList<String> excludeList =obj.getExcludeFolders();
				
				
				List<String> lFiles = new ArrayList<>();
				for(int k=0;k<driveList.size();k++)
				{
					FindHashValue h=new FindHashValue();
		
					rootPath=driveList.get(k);
				 	lFiles =h.displayFiles(rootPath,excludeList);
										
				}
				break;
		case 2:

			MyDatabase obj2 = new MyDatabase();
			System.out.println("Duplicate Files:");
		//	obj2.checkHashValue();
			obj2.CheckHash();
			break;
		case 3:
			System.out.println("Reset Table And Exited From Execution");
			MyDatabase obj3 = new MyDatabase();
			obj3.ClearTable();
			System.exit(1);
			break;
		
		}// end of switch
		}while(iChoice !=3);//end of do-while
	}


List<String> displayFiles(String Rpath,List<String> excludeList) throws IOException , NoSuchAlgorithmException, SAXException, ParserConfigurationException
{
	File file= new File(Rpath);
//	System.out.println("Started Process For==>"+file.getName());
	File[] listOfFiles = file.listFiles();
	if(listOfFiles != null)
	 {
		System.out.println("Files location :"+file);
		System.out.println("files length="+listOfFiles.length);			
		for(int i=0;i<listOfFiles.length;i++)
	        {
            	File fTemp = listOfFiles[i];
            	if(excludeList.contains(fTemp.getName()))
            	// || fTemp.getName().equals("software")==true || fTemp.getName().equals("Shital")==true|| fTemp.getName().equals("Ddrives data")==true)
            		{
            	    	System.out.println(" "+fTemp.getName());	
            	        continue;
            		}
            	    else {
            	    		if(listOfFiles[i].isFile())
            		       	{
            	    			String HoldPath=listOfFiles[i].getPath();	
            			//		System.out.println("Before inserting records..");
            					// Create Object of database class which is MyDatabase
            							  
            					MyDatabase obj = new MyDatabase();
            					MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            					String stringHash = getFileChecksum(md5Digest,fTemp);
            					// Access method of Mydatabase class
            							 
            					obj.insert(HoldPath,stringHash);
            			//		System.out.println("Records inserted..");
            		//			System.out.println("File Added="+listOfFiles[i].getPath());
            				}	 
            				else if(listOfFiles[i].isDirectory())
            				{
            			//	System.out.println("Directory Found= "+listOfFiles[i].getName());
            				System.out.println(listOfFiles[i].getName());
            				String subfolder=listOfFiles[i].getPath();
            				List<String> tempList;
            				tempList=displayFiles(subfolder,excludeList);
            				//	list.addAll(tempList);
            				System.out.println(listOfFiles[i].getPath());
            				}
            	         }
            	     }//end for loop
            	 }// end if
            	System.out.println("List Data="+list);
            	return list;
	}
	 static String getFileChecksum(MessageDigest digest, File file) throws IOException
	 {
	    //Get file input stream for reading the file content
	     FileInputStream fis = new FileInputStream(file);

	     //Create byte array to read data in chunks
	 	byte[] byteArray = new byte[1024];
	 	int bytesCount = 0;
	 	//Read file data and update in message digest
	 	while ((bytesCount = fis.read(byteArray)) != -1) 
	 	{
	 	       digest.update(byteArray, 0, bytesCount);
	 	
	 	};
	   //close the stream We don't need it now.
	 	
	     fis.close();
	   //Get the hash's bytes
	     byte[] bytes = digest.digest();
	   //This bytes[] has bytes in decimal format;
	  //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	  //return complete hash
	   return sb.toString();
}
}
