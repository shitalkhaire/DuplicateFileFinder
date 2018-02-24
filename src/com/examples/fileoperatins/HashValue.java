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

	public class HashValue 
	{
		int i;
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
					String strAllFile = "D:\\Test\\";
					
					
					String s=h1.displayFiles(strAllFile);
					//String listOfFiles = null;
					// s=listOfFiles;
					
					System.out.println("List of Files ="+s);

						
						break;
						
					case 2:
						//Create checksum for this file
						File file1 = new File("D:\\Test\\file1.txt");
						File file2 = new File("D:\\Test\\file2.txt");
						//Use MD5 algorithm
						MessageDigest md5Digest = MessageDigest.getInstance("MD5");
						 
						//Get the checksum
						String checksum1 = getFileChecksum(md5Digest, file1);
						String checksum2 = getFileChecksum(md5Digest, file2);
				//		boolean isTwoEqual = h1.contentEquals(file1, file2);
						
						//see checksum
						System.out.println("Hash of First File: "+checksum1);
						System.out.println("Hash of Second File: "+checksum2);
						if((checksum1).equals(checksum2))
						//if((checksum1).equals(cheksum2))
						//Files.asByteSource(file1).contentEquals(Files.asByteSource(file2));
						{
						 System.out.println("Given File is  Duplicate..");
						}
						else
						{
							System.out.println("Given File is  Not-Duplicate..");	
						}
					
						

						break;
					case 3:
						System.out.println("Exit from program");

					      System.exit(1);
					      
					  					
					}
			}while(choice != 3);
		}

		  
		String displayFiles(String strAllFile) throws IOException , NoSuchAlgorithmException
		 {
			 File folder =new File(strAllFile);
			 File[] listOfFiles = folder.listFiles();
				System.out.println("Files location :"+folder);
				for(int i=0;i<listOfFiles.length;i++)
				{
					System.out.println("value of i :="+i);
					if(listOfFiles[i].isFile())
					{
						System.out.println(""+listOfFiles[i].getName());
						System.out.println(""+listOfFiles[i].getPath());
					}
					else if(listOfFiles[i].isDirectory())

					{
						System.out.println("Directory location:"+listOfFiles[i]);
						System.out.println("\nDirectory:\n"+listOfFiles[i].getName());
						

					}
				//	System.out.println("value of i :="+i);
				}
				
				
				for (File file : listOfFiles) 
				{
				   if (file.isFile()) 
				   {
				        System.out.println(file.getName());
				    }
				  
					//return strAllFile;
				}
			//	System.out.println("value of i :="+i);
				return listOfFiles[i].getPath();
				
			//	return "Shital";
				
				
		 }
	 static String getFileChecksum(MessageDigest digest, File file) throws IOException
		{
		    //Get file input stream for reading the file content
		    FileInputStream fis = new FileInputStream(file);
		     
		    //Create byte array to read data in chunks
		    byte[] byteArray = new byte[1024];
		    int bytesCount = 0;
		      
		    //Read file data and update in message digest
		    while ((bytesCount = fis.read(byteArray)) != -1) {
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


		
			


	

		