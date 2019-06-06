package com.examples.fileoperatins;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class HashValue 
{
	static //	int i;
	String Fpath,Fpath2;
	String ans;
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException 
	{
		// Program Using SwitchCase..
		//	System.out.println("Welcome...");
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
			// this variable declare outside case because it should be accessible in case 2
			List<String> list1;
			 HashValue h1=new HashValue();
			 String strAllFile = "E:\\Test\\";
			 switch(choice)
			{
			case 1:
				// only printing files 
				list1 =h1.displayFiles(strAllFile);
				for(int i=0;i<list1.size();i++)
				{
					Fpath=list1.get(i);
					//System.out.println(" "+Fpath);
				}
			    break;
			case 2:
					list1 =h1.displayFiles(strAllFile);
					MessageDigest md5Digest = MessageDigest.getInstance("MD5");
					for(int i=0;i<list1.size();i++)
					{
						for(int j=i;j<list1.size();j++)
						{
							//Finding checksum for 1st file.
							Fpath=list1.get(i);
							File file1 = new File(Fpath);
							String Checksum1 = getFileChecksum(md5Digest,file1);
 							//Finding checksum for 2nd file.
							Fpath=list1.get(j);
 							File file2 = new File(Fpath);
 							String Checksum2 = getFileChecksum(md5Digest,file2);
	 						// Comparison between two checksum values
 							if(i!=j)
							{
 								if(Checksum1.equals(Checksum2))
								{
 									System.out.println("Duplicate Files Are:"+file1+"\t"+file2);
	 							 }
	 						}	                   
			 			}
			 	    }
					break;
			case 3:
					System.out.println("Exit from program");
					System.exit(1);
				}
			}while(choice != 3);
		}
List<String> displayFiles(String strAllFile) throws IOException , NoSuchAlgorithmException
{
	File folder =new File(strAllFile);
	File[] listOfFiles = folder.listFiles();
	System.out.println("Files location :"+folder);
	System.out.println("files length="+listOfFiles.length);
	//	String str[] = new String[20];
	List<String> list1 = new ArrayList<String>();
	
	for(int i=0;i<listOfFiles.length;i++)

		if(listOfFiles[i].isFile())
		{
			String HoldPath=listOfFiles[i].getPath();		
			list1.add(""+HoldPath);
			System.out.println(""+listOfFiles[i].getPath());
		}	 
		else if(listOfFiles[i].isDirectory())
		{
			System.out.println(listOfFiles[i].getName());
			String subfolder=listOfFiles[i].getPath();
			List<String> tempList;
			tempList=displayFiles(subfolder);
			list1.addAll(tempList);
			System.out.println(listOfFiles[i].getPath());
			
		}
								 			 
		System.out.println("List Elements Are:"+list1);
		return list1;
	}
								 			
// To get File checksum value of files.
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
   //close the stream; We don't need it now.
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
						 		
							