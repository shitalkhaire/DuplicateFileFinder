package com.examples.fileoperatins;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHashValue {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException 
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		System.out.println("file data "+Paths.get("D:\\Test\\file1.txt"));
		try (InputStream is = Files.newInputStream(Paths.get("D:\\Test\\file1.txt"));
		     DigestInputStream dis = new DigestInputStream(is, md)) 
		{
		  /* Read decorated stream (dis) to EOF as normal... */
		}	
		
		
		byte[] digest = md.digest();
		System.out.println(""+digest);
	}
	//System.out.println("file data "+Paths.get("file1.txt"));
}
