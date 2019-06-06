package com.examples.fileoperatins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/* 
 * 	Class reads config.xml file.
 * 	It has fXmlFile object which create new instance of configurationClass by using constructor configurationClass(String strConfFile),
 *  strConfFile is nothing but config.xml file.
 * 	Here we used DocumentBuilder methods for reading the xml file's data.
 
 */
public class configurationClass
{
	NodeList nlExcludeFolder,drvList;//this NodeList variables we can access outside the class.
	
	 configurationClass(String strConfFile) throws ParserConfigurationException, SAXException, IOException // constructor
	 {
		 File fXmlFile = new File(strConfFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

		 nlExcludeFolder = doc.getElementsByTagName("ExcludeFolders");
		 drvList = doc.getElementsByTagName("DisplayDrives");
	 }
	 // here mthd gets Drives names 
	 public	ArrayList<String> getDrives()
		{
			ArrayList <String> list1 = new ArrayList<String>();
			for (int i = 0; i < drvList.getLength(); i++)
			{
				
				Node ndrive= drvList.item(i);
				Element eElement = (Element)ndrive;
				NodeList  nName = eElement.getElementsByTagName("drive");
				
			//	System.out.println("Display Drives..");

				for (int j = 0; j < nName.getLength(); j++) 
				{
					
					Node node= nName.item(j);
		//			System.out.println(" "+node.getTextContent());
					list1.add(node.getTextContent());
					
				}
		
}
	System.out.println("Drives list is:=>"+list1);
	return list1;
	
			
}
	 	
public	ArrayList<String> getExcludeFolders( )
	{
		ArrayList <String> list2 = new ArrayList<String>();
		for (int i = 0; i < nlExcludeFolder.getLength(); i++) {
			
			Node nExcludeFolder= nlExcludeFolder.item(i);
			Element eElement = (Element)nExcludeFolder;
			NodeList  nName = eElement.getElementsByTagName("name");
			
		//	System.out.println("Exclude Folders..");

			for (int j = 0; j < nName.getLength(); j++) {
				
				Node node= nName.item(j);
	//			System.out.println(" "+node.getTextContent());
				list2.add(node.getTextContent());
				
			}
	}
		System.out.println("Exclude Folders :"+list2);
		return list2;
	}
			
}
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

			
