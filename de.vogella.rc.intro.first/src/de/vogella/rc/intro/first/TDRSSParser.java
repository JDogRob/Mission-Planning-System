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

public class TDRSSParser {
	
	public static ArrayList<String>              data = new ArrayList<String>();
	
	public static ArrayList<String>          PassType = new ArrayList<String>();
	public static ArrayList<String>       PassAntenna = new ArrayList<String>();
	
	public static ArrayList<JaretDate>  PassStartTime = new ArrayList<JaretDate>();
	public static ArrayList<JaretDate>    PassEndTime = new ArrayList<JaretDate>();

	public static DateFormat format = new  SimpleDateFormat ("yyyy/ddd:HH:mm:ss");
	public JaretDate jaretDateTimeObject;
	
	public String line;
	public static String fileName;
	
	
	public TDRSSParser(String PassedFileName){
		
		 fileName = PassedFileName;
		 
		 data.clear();
		 PassType.clear();
		 PassAntenna.clear();
		 PassStartTime.clear();
		 PassEndTime.clear();
		 	 
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
	 * parseElements will step through a standard TDRSS/USN text file. Accomplishes:
	 * 1. Parse pass type: COTS pass types are TDRSS / USN / NEN 
	 * 2. Parse antenna name 
	 * 3. Parse pass start time
	 * 4. Parse pass end time 
	 */
	
	 public void parseElements() {
		 
		 TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
		 format.setTimeZone(gmtTimeZone);
		 
		 for (int i=0; i<(data.size()); i++) {
			
			 /**
			  * Parse the pass name
			  */
	 
			 List<String> lineItems = Arrays.asList(data.get(i).split("\\s*, \\s*"));
			
			 try {
				 
				 String passType          = lineItems.get(0);
				 String passAntenna       = lineItems.get(2); 
				 
				 PassType.add(passType);
				 PassAntenna.add(passAntenna);
				 
				 Date startDateTimeObject = format.parse(lineItems.get(3));
				 Date endDateTimeObject   = format.parse(lineItems.get(4));
				 
				 jaretDateTimeObject = new JaretDate(startDateTimeObject);
				 
				 System.out.println(jaretDateTimeObject.toDisplayString());
				 
				 PassStartTime.add(jaretDateTimeObject);
				
				 jaretDateTimeObject = new JaretDate(endDateTimeObject);
				 
				 PassEndTime.add(jaretDateTimeObject);
				 
			 }
			 
			 catch(ParseException pe) {
		         
				 System.out.println("ERROR: could not parse date in string");
			
			 }
			 
			} 
		 
	 }
	
	 public static int getPassTypeArraySize() {
		 
		 return PassType.size();	 
	 }
	 
	 public static int getPassAntennaArraySize() {
			 
		 return PassAntenna.size();
		 
	}
	 
	 public static int getPassStartTimeArraySize() {
		 
		 return PassStartTime.size();	 
	 }
 
	 public static int getPassEndTimeArraySize() {
		 
		 return PassEndTime.size();
	 }
	 	 
	 public static String getPassTypeArrayValue(int index) {
		 
		 return PassType.get(index);	 
	 }
	 
	 public static String getPassAntennaArrayValue(int index) {
			 
		 return PassAntenna.get(index);	 
	}
	 
	 public static JaretDate getPassStartTimeArrayValue(int index) {
		 
		 return PassStartTime.get(index);
	 }
 
	 public static JaretDate getPassEndTimeArrayValue(int index) {
		 
		 return PassEndTime.get(index);
	 }
	 
}
	 




	
		  
		  
	

