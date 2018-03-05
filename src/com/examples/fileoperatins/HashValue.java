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
 import java.util.Iterator;
 import java.util.List;
 
 	
 		public class HashValue 
 		{
 		static //	int i;
 			String Fpath;
 			public static void main(String[] args, MessageDigest digest, File file) throws NoSuchAlgorithmException, IOException 
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
 						
 						List<String> list =h1.displayFiles(strAllFile);
 						for(int i=0;i<list.size();i++)
 						{
 							 Fpath=list.get(i);
 						System.out.println(" "+Fpath);
 						
 						}
 						
					      break;
 							
 						case 2:
 							//Create checksum for this file
 							
 							HashValue h2=new HashValue();
 							
 							
 							List<String> list1 =h2.displayFiles(Fpath);
 							List<String> list2=h2.getFileChecksum(digest, file);
 							for(int i=1;i<list1.size();i++)
 				 			        {
 				 					
 				 					//Create checksum for this file
 			 							File file1 = new File(Fpath);

 			 		//					File file2 = new File("D:\\Test\\file2.txt");
 			 							
 			 							//Use MD5 algorithm
 			 						
 			 							MessageDigest md5Digest = MessageDigest.getInstance("MD5");
 			 							 
 			 							//Get the checksum
 			 							List<String> checksum1 = getFileChecksum(md5Digest, file1);
 			 							
 			 							
 			 							//see checksum
 			 							System.out.println("Hash of Files: "+checksum1);

 			 		/*	               //if((checksum1).equals(cheksum2))
 			 							//Files.asByteSource(file1).contentEquals(Files.asByteSource(file2));
 			 							{
 			 							 System.out.println("Given File is  Duplicate..");
 			 							}
 			 							else
 			 							{
 			 								System.out.println("Given File is  Not-Duplicate..");	
 			 							}
 			 						
 				 			        }
 			 	*/
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
 			 				List<String> list = new ArrayList<String>();
 			 				
 			 			
 			 	
 			 					for(int i=0;i<list.size();i++)
 			 			 
 			 			        {
 			 					
 			 						if(listOfFiles[i].isFile())
 			 						{
 //			 							System.out.println(""+listOfFiles[i].getName());
// 			 							System.out.println(""+listOfFiles[i].getPath());
 			 						    System.out.println("Path of Files:=" +listOfFiles[i].getPath());
 			 						  list.add(" "+listOfFiles[i].getPath());
 			 						
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
 	 					  
 	 						
 	 					}
 	 					return  list; 
 	 					
 	 					
 	 				//	return "Shital";
 	 					
 	 					
 	 			 }
 	 		  static List<String> getFileChecksum(MessageDigest digest, File file) throws IOException
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
 	 			   List<String> list3 = new ArrayList<String>();
 	 			   for(int i=0;i<list3.size();i++)
 	 			   {
 	 			      
 	 			   
          
 	 			  StringBuilder sb = new StringBuilder();
 	 			    for(int j=0; j< bytes.length ;j++)
 	 			    {
 	 			    
 	 			       sb.append(Integer.toString((bytes[j] & 0xff) + 0x100, 16).substring(1));
 	 			       list3.add(" "+sb);
 	 			    }
 	 		     
 	 			  
 	 			   }
 	 			    //return complete hash
 	 			 
 	 			   // return sb.toString();
 	 			 
 	 			    return list3;
 	 			}
 	 		}
 			 					