	package com.examples.fileoperatins;
	
	import java.io.BufferedReader;
	import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
	import java.io.InputStreamReader;
import java.security.MessageDigest;
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
			for(int i =1; i < roots.length-1 ; i++)
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
			}
			
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		for(int i=0;i<list.size();i++)
 	    {
 			for(int j=i;j<list.size();j++)
 			{
 								 			
 					   //Finding checksum for 1st file.
						Fpath=list.get(i);
							File file1 = new File(Fpath);
							String Checksum1 = getFileChecksum(md5Digest,file1);
						   
						//Finding checksum for 2nd file.
						Fpath=list.get(j);
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
