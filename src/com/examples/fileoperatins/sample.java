package com.examples.fileoperatins;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sample
{

	public static void main(String[] args) 
	{
		

		File folder =new File("D:\\Test");
		File[] listOfFiles = folder.listFiles();
		System.out.println("Files location :"+folder);
		//Files.asByteSource(file1).contentEquals(Files.asByteSource(file2));
		for(int i=0;i<listOfFiles.length;i++)
		{
			if(listOfFiles[i].isFile())
			{
				System.out.println(""+listOfFiles[i].getName());
				
			}
			else if(listOfFiles[i].isDirectory())

			{
				System.out.print("Directory location:"+listOfFiles[i]);
				System.out.println("\nDirectory:\n"+listOfFiles[i].getName());
			}
		}
		 byte[] buffer= new byte[8192];
		    int count;
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
		    while ((count = bis.read(buffer)) > 0) {
		        digest.update(buffer, 0, count);
		    }
		    byte[] hash = digest.digest();
		    System.out.println(new BASE64Encoder().encode(hash));
		
		
		    
	}

}
