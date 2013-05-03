package de.vogella.rc.intro.first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import de.jaret.util.date.JaretDate;
import de.jaret.util.date.JaretDateFormatter;

public class DeltaVParser {
	
	public static ArrayList<String>           data = new ArrayList<String>();
	
	/**
	 * Careful of the clever anagram... 
	 */
	
	public static ArrayList<JaretDate>  EventTime  = new ArrayList<JaretDate>();
	public static ArrayList<String>     EventItem  = new ArrayList<String>();
	

	public static DateFormat format = new  SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
	
	public JaretDate jaretDateTimeObject;
	
	public String line;
	public static String fileName;
	
	public DeltaVParser(String PassedFileName){
		
		 fileName = PassedFileName;
		 
		 data.clear();

		 EventTime.clear();
		 EventItem.clear();
		 	 
	}
	

	public void OpenFile() {
	   
		try {
            
          FileReader fr = new FileReader(fileName);
          BufferedReader br = new BufferedReader(fr);
            
          while((line = br.readLine()) != null) {
                
             data.add(line);
            
            }
          
		}
       
		catch (FileNotFoundException fN) {
	            
	       fN.printStackTrace();
	            
	        }
	        
	    catch (IOException e) {
	            
	       System.out.println(e);
	            
	        }         
		
	}
	 
	
	/**
	 * parseElements will step through a standard STK  text file. Accomplishes:
	 * 1. Parses all Delta-V burns
	 * 2. Creates Jaret Date Objects
	 */
	
	 public void parseElements() {
		 
			
		 for (int i=0; i<(data.size()); i++) {
			
			 /**
			  * Parse the Event Time
			  */
	 
			 List<String> lineItems = Arrays.asList(data.get(i).split(","));
			
			 try {
				 
				 String eventItem  = lineItems.get(1); 
				 System.out.println(eventItem);
				 EventItem.add(eventItem);
				 
				 Date eventTimeDateObject = format.parse(lineItems.get(0));
				 
				 System.out.println(eventTimeDateObject.toString());
				 
				 JaretDate jaretDateTimeObject = new JaretDate(eventTimeDateObject);
				 EventTime.add(jaretDateTimeObject);
			 }
			 
			 catch(ParseException pe) {
		         
				 System.out.println("ERROR: could not parse date in string");
			
			 }
			 
			} 
		 
	 }
	
	 public static int getEventTimeArraySize() {
		 
		 return EventTime.size();	 
	 }
	 
	 public static int getEventItemArraySize() {
			 
		 return EventItem.size();
		 
	}
	 	 
	 public static String getEventItemArrayValue(int index) {
		 
		 return EventItem.get(index);	 
	 }
	 
	 public static JaretDate getEventTimeArrayValue(int index) {
		 
		 return EventTime.get(index);
	 }
	 
}
	 




	
		  
		  
	

