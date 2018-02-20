package com.examples.fileoperatins;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BASE64Encoder {
	public static void main(String args[])
	{
		
		BASE64Encoder.encode(hash2);
		//be.encode(hash2);
		
	
	private char[] encode(byte[] hash) throws NoSuchAlgorithmException, IOException
	{
		 byte[] buffer= new byte[8192];
		    int count;
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("file1.txt"));
		    while ((count = bis.read(buffer)) > 0)
		    {
		        digest.update(buffer, 0, count);
		    }
		    byte[] hash = digest.digest();
		    System.out.println(new BASE64Encoder().encode(hash));
		
	}
	return;
	}

	

}

