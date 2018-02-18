package com.examples.fileoperatins;

import java.io.BufferedInputStream;
import org.bouncycastle.util.encoders.Base64Encoder;

//import java.io.BufferedInputStream.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
public class HashValueFiles {

	public static void main(String[] args) throws Exception 
	{
	
		byte[]buffer =new byte[8192];
		int count;
		MessageDigest digest =MessageDigest.getInstance("SHA-256");
        byte[] data = null;
		byte[] hash = digest.digest(data);
        System.out.println(new BASE64encoder().encode(hash));	  
	}
	}
