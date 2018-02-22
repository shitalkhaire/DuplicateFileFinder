package com.examples.fileoperatins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashValue 
{
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException 
	{
		
	   
		// Program Using SwitchCase..
		System.out.println("1.Display All Files");
		System.out.println("2.Display  HashValue of Files");
		System.out.println("3.Quit..");
		
		int  choice;
		do
		{
			System.out.println("Enter your choice:=");
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			//System.out.println(+choice);
			 choice=Integer.parseInt(b.readLine());
			switch(choice)
				{
				
				case 1:
					HashValue h1=new HashValue();
				String strAllFile = null;
				
				h1.displayFiles(strAllFile);
					
					break;
					
				case 2:
					HashValue h=new HashValue();
					h.getHashofFile("D:\\Test\\file1.txt");      // non-static method
					//HashValue.getHashofFile("D:\\Test\\file1.txt"); //static method
					h.getHashofFile("D:\\Test\\file2.txt");
				
					//	Files.hash(file, HashValue.getHashofFile()); 
					
					break;
				case 3:
					System.out.println("Exit from program");

				      System.exit(1);
				      
				  					
				}
		}while(choice != 3);
	}

	 String getHashofFile(String strFileName) throws IOException, NoSuchAlgorithmException
	{
		MessageDigest md1 = MessageDigest.getInstance("MD5");
		
		//MessageDigest md2 = MessageDigest.getInstance("MD5");
		
		System.out.println("file data "+Paths.get("D:\\Test\\file1.txt"));
		System.out.println("file data "+Paths.get("D:\\Test\\file2.txt"));
		
		try (InputStream is = Files.newInputStream(Paths.get(strFileName));
		 DigestInputStream dis = new DigestInputStream(is, md1)) 
		{
		  /* Read decorated stream (dis) to EOF as normal... */
		}	
		
		byte[] digest1 = md1.digest();
		byte[] digest2 = md1.digest();
		System.out.println("Hash value of file:-"+digest1);
	//	return digest1.toString();		
		System.out.println("Hash value of file:-"+digest2);
	//Object file;
	//Files.hash(file, HashValue.md5());    ---added new line here
		
//if(true)       when i was used this that time no any problems were shown      
	return digest1.toString();
	
//	return digest2.toString();
		
			
		
		
	}
	 
	

	String displayFiles(String strAllFile) throws IOException , NoSuchAlgorithmException
	 {
		 File folder =new File("D:\\Test\\");
		 File[] listOfFiles = folder.listFiles();
			System.out.println("Files location :"+folder);
			for(int i=0;i<listOfFiles.length;i++)
			{
				if(listOfFiles[i].isFile())
				{
					System.out.println(""+listOfFiles[i].getName());
					
				}
				else if(listOfFiles[i].isDirectory())

				{
					System.out.println("Directory location:"+listOfFiles[i]);
					System.out.println("\nDirectory:\n"+listOfFiles[i].getName());
					

				}
			}
			
			for (File file : listOfFiles) 
			{
			   if (file.isFile()) 
			   {
			        System.out.println(file.getName());
			    }
			  
				//return strAllFile;
			}
			return strAllFile;
			
	 }
}


	
		
	

		