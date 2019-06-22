package com.examples.fileoperatins;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MyDatabase 
{
/**
*	 Connect to the test.db database
*    @return the Connection object
*         
*/
	public Connection connect() 
	{
	// SQLite connection string
	String url = "jdbc:sqlite:E://Shital//EclipseWorkspace/SampleProject/sample.db";
	Connection conn = null;
	      
		try {
	    
			conn = DriverManager.getConnection(url);
	        
		}
		
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
	    }
	    
		return conn;
	    
	}
	  
	    
	public void insert(String Filepath,String Filehash) 
	{
		/*
		 * Query of Table Creation in database:CREATE TABLE TestFile(File_ID INTEGER, File_path varchar, File_hash varchar, PRIMARY KEY(File_ID) )
		 */
		String sqlInsert = "INSERT INTO TestFile(File_path,File_hash) VALUES(?,?)";
	    try (Connection conn = this.connect();
	    PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) 
	    {
	    	pstmt.setString(1, Filepath);
	    	pstmt.setString(2, Filehash);
	    	pstmt.executeUpdate();
	    } catch (SQLException e) 
	    {
	    	System.out.println(e.getMessage());
	    }
	   }
	@Deprecated
	public void checkHashValue() throws SQLException
	{
	 
	 Connection Connectionstring= connect();
	 Statement stmt = Connectionstring.createStatement();
	 // Query of database:- finding duplicate files. 	
	//  String sql2 = "select File_Path from TestFile where File_hash='96a83c19012c580ab4229602ae91b74f' and File_ID=3;";
	String sqlSelect = "SELECT * from TestFile";
	ResultSet rs1 = stmt.executeQuery(sqlSelect);
	        
	//Another query to finding duplicate files from specified path or location.
	//select File_hash, File_path, count(*) from TestFile group by File_hash having count(*) > 1;
	       
	//Extract data from result set
		while(rs1.next())
		{
		     //Retrieve by column name
		     int fileId  = rs1.getInt("File_ID"); 
		     String filePath = rs1.getString("File_path");
		     
		    File f1= new File(filePath);
	    	String fileName =f1.getName();
		    String fileHash = rs1.getString("File_hash");
		    Statement stmt1 = Connectionstring.createStatement();
		   // String duplHash ="SELECT * FROM TestFile WHERE File_hash='"+fileHash+"'";
		    String duplHash ="Select * from TestFile where File_Hash='"+fileHash+"' AND Not  File_ID ='"+fileId+"'";
		    ResultSet rs2 = stmt1.executeQuery(duplHash);
		      while(rs2.next())
		      {
		    	  int fileId2 = rs2.getInt("File_ID");
		    	  String dupfilePath = rs2.getString("File_path");
		    	  
		    	  File f2= new File(dupfilePath);
		    	  String dupfileName =f2.getName();
		    	  if(fileId2 > fileId)
		    	  {
		    	  System.out.println(fileId+fileName+" Has a duplicate copies:=\t"+dupfileName+" Present at "+dupfilePath);
		    	  }
		      }//end of inner while
	          
		 }//end of outer while
	    }
	public void CheckHash() throws SQLException
	{
		 Connection Connectionstring= connect();
		 Statement st = Connectionstring.createStatement();	
		 //Here used nested query to find same hashvalue of files.
		 String query ="select * from TestFile where File_hash in (select File_hash from TestFile group by File_hash having count(*) > 1)order by file_hash";
		 ResultSet newRs = st.executeQuery(query);
		 String lastHash="";
		 String lastFile="";
		 while(newRs.next())
	      {
	    	  int fId = newRs.getInt("File_ID");
	    	  String dupfilePath = newRs.getString("File_path");
	    	  String fHash =newRs.getString("File_hash");
	    	  
	    	  File f2= new File(dupfilePath);
	    	  String fName =f2.getName();
	    	   
	    	  if(!lastHash.equals(fHash))
	    	  {
	    		  System.out.println(" File:-"+fName);
	    		  lastFile = fName;
	    		
	    		 // System.out.println("-----------------------------------------------------------------------");
	    	  }
	    	  else
	    	  {
	    		  System.out.println(" "+fId+" "+lastFile+" Has a duplicate copies  "+fName+" Present at "+dupfilePath);
	    		  System.out.println("-----------------------------------------------------------------------");
	    		  
	    	  }
	    	  lastHash = fHash;
	    	  
	      }//end of while
         
	
	}
	//Method for Delete all records from table: TestFile.
	public void ClearTable() throws SQLException 
	{
		Connection Connectionstring2= connect();
		Statement stmt2 =Connectionstring2.createStatement();
		String clearRecords ="Delete from TestFile";
		int rs3 = stmt2.executeUpdate(clearRecords);
		
	}
	}
	
	    