package de.vogella.rc.intro.first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import de.jaret.util.date.JaretDate;

public class ParseOrbitEventsFile {
	
	public ArrayList<String>           data = new ArrayList<String>();
	public ArrayList<JaretDate> ElementTime = new ArrayList<JaretDate>();
	public ArrayList<String>    ElementName = new ArrayList<String>();
	public ArrayList<String>    ElementRote = new ArrayList<String>();
	
	private DateFormat format = new  SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	private JaretDate jaretDateTimeObject;
	
	private String line;
    public static String fileName;
	
	
	public ParseOrbitEventsFile(String PassedFileName){
		
		 fileName = PassedFileName; 
		 
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
	 * parseElements will step through the the Mission Planning text. Accomplishes:
	 * 1. Parse Element Time -> Creates JaretDate Object for use in Ghant Chart
	 * 2. Parse Element Name 
	 * 3. Parse Rote Expansion 
	 */
	
	 public void parseElements() {
		 
		 for (int i=0; i<(data.size()); i++) {
			
			 /**
			  * Parse the element time. Create new JaretDate Object from the time.
			  */
			 
			 if (data.get(i).contains("Element time:")) {
				  
				 /**
				 * Return a substring by removing "Element time:"
				 */
				 
				 String dateTimeString = data.get(i).substring(15); 
						 
				 try {
					 
					 Date dateTimeObject = format.parse(dateTimeString);
					 jaretDateTimeObject = new JaretDate(dateTimeObject);
					 
					 ElementTime.add(jaretDateTimeObject);
					 
				 	}
				 

				 catch(ParseException pe) {
		         
					 System.out.println("ERROR: could not parse date in string");
				
				 	}
				 
					/**  	 
					* Parse the Element name. If all is working correctly this should be
					* the element name associated with the time just parsed.  The next string
					* will be the full ROTE Expansion. 
					*/	 
		
					String elementNameString = data.get(i + 3).substring(15);
					String elementRoteString = data.get(i + 4);
					
					ElementName.add(elementNameString);	
					ElementRote.add(elementRoteString);
					
				}
			
			} 
		 
	 }
	 
	 public Iterator<JaretDate> iterateElementTime() {
		 
			return ElementTime.iterator();
			 
		 }
	 
	 public Iterator<String> iterateElementName() {
		 
		return ElementName.iterator();
		 
	 }
	 
	 public Iterator<String> iterateElementRote() {
		 
			return ElementRote.iterator();	 
		 }

	 public int getElementNameArraySize() {
		 
		 return ElementName.size();	 
	 }
	 
	 public int getElementTimeArraySize() {
			 
		 	return ElementTime.size();	 
		 }
	 
	 public int getElementRoteArraySize() {
		 
		 return ElementRote.size();	 
	 }
 
	 public JaretDate getElementTimeArrayValue(int index) {
		 
		 return ElementTime.get(index);
		 
	 }
	 
	 public String getElementNameArrayValue(int index) {
		 
		 return ElementName.get(index);
		 
	 }
	 
	 public String getElementRoteArrayValue(int index) {
		 
		 return ElementRote.get(index);
		 
	 }
	 
}




	
		  
		  
	

