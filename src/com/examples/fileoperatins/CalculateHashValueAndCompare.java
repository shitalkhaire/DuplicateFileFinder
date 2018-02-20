package com.examples.fileoperatins;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalculateHashValueAndCompare 
{

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		
	// TODO Auto-generated method stub

		byte[] data = "test".getBytes("UTF8");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        System.out.println(new BASE64Encoder().encode(hash));


	}

}
