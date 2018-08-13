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
import java.util.Scanner;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
											
	public class DirectoryHash
	{
		static	String DriveList,GivenDrives,Rpath;
		static ArrayList<String> arrlist = new ArrayList<String>();
		static  List<String> list = new ArrayList<String>();
		
		public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SAXException, ParserConfigurationException
		{
			// returns pathnames for files and directory
			File[] roots = File.listRoots();
			System.out.println("\t\n List Of Drives...");
			for(int i =1; i < roots.length-1 ; i++)
			System.out.println("\tRoot["+i+"]:" + roots[i]);
			//object is Created of configurationClass
			
			configurationClass obj = new configurationClass ("Config.xml");

			//		System.out.println("Enter Directories and Exit to exit:=>");
			ArrayList<String> lDriveFiles = obj.displayDrives();
			
			ArrayList<String>ExcludeList =obj.getExcludeFolders();
			//	Scanner sc = new Scanner(System.in);
			//while(true)
      
//  	System.out.println(""+arrlist);
			List<String> lFiles = new ArrayList<>();
			for(int k=0;k<lDriveFiles.size();k++)
			{
				DirectoryHash h=new DirectoryHash();
			 	Rpath=lDriveFiles.get(k);
			 	lFiles =h.displayFiles(Rpath);
									
			}
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			System.out.println("ldriv "+lFiles);
			for(int p=0;p<lFiles.size();p++)
			{
				for(int q=p;q<lFiles.size();q++)
				{
				   //Finding checksum for 1st file.
					String Fpath=lFiles.get(p);
					File file1 = new File(Fpath);
					String Checksum1 = getFileChecksum(md5Digest,file1);
					//Finding checksum for 2nd file.
					Fpath=lFiles.get(q);
					File file2 = new File(Fpath);
					String Checksum2 = getFileChecksum(md5Digest,file2);
					// Comparison between two checksum values
					if(p!=q)
					{
						if(Checksum1.equals(Checksum2))
						 {
							System.out.println("Duplicate Files Are:"+file1+"\t"+file2);
																		
						 }
					}	                   
	 			}
	 	    
			}
	}
	// displayFiles method
	List<String> displayFiles(String Rpath) throws IOException , NoSuchAlgorithmException, SAXException, ParserConfigurationException
	{
		configurationClass obj = new configurationClass ("Config.xml");
		ArrayList<String> LExclude=obj.getExcludeFolders();
		//System.out.println(""+ LExclude);
		
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
	           // 	if(fTemp.getName().equals("$RECYCLE.BIN") || fTemp.getName().equals("System Volume Information"))
              //if(fTemp.getName().equals("$RECYCLE.BIN")!=true || fTemp.getName().equals("System Volume Information")!=true)
            	
         	if(LExclude.contains(fTemp.getName()))
    // 			|| fTemp.getName().equals("software")==true || fTemp.getName().equals("Shital")==true|| fTemp.getName().equals("Ddrives data")==true)
				{
         		System.out.println(" "+fTemp.getName());	
         		continue;
				}
         	else {
	            		if(listOfFiles[i].isFile())
	            		{
						  String HoldPath=listOfFiles[i].getPath();		
						  list.add(""+HoldPath);
	//					  System.out.println("File Added="+listOfFiles[i].getPath());
						}	 
						else if(listOfFiles[i].isDirectory())
						{
		//					System.out.println("Directory Found= "+listOfFiles[i].getName());
							System.out.println(listOfFiles[i].getName());
							String subfolder=listOfFiles[i].getPath();
							List<String> tempList;
							tempList=displayFiles(subfolder);
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
//	 System.out.println("jjjjjjjjj"+file.getName());
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
