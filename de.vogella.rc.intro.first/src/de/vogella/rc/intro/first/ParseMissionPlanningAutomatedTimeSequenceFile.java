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

public class ParseMissionPlanningAutomatedTimeSequenceFile implements Runnable{
	
	ArrayList<Date> dateObjects = new ArrayList<Date>(); 
	ArrayList<String> data = new ArrayList<String>(); 
	ArrayList<String> commands = new ArrayList<String>();
	
	DateFormat eachDate = new  SimpleDateFormat ("yyyy/MM:dd:HH:mm:ss");
	
	String line;
	
	 public void OpenFile() {
	   
		try {
            
          FileReader fr = new FileReader("/Users/Jason/Desktop/COTS_2012_054_0000_v096.scr");
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
	 
	 public void ParseCommands() {
		 for (int i=0; i<(data.size()); i++) {
			 
			 int indexOfDollarSign = (data.get(i).indexOf("$"));
			 String atsCommand = data.get(i).substring(0, indexOfDollarSign);
			 
			 int atsCommandLength = atsCommand.length();
			 int lastChar = atsCommandLength - 2;
			 char last = atsCommand.charAt(lastChar);

			 if (last == ';'){
				 atsCommand = atsCommand.substring(0, atsCommandLength - 2);
			 }
			 
				 
			 commands.add(atsCommand);
			 
		 }	 
	 }
	 
	 
	 public void ParseDates() {
		    
	        for (int i=0; i<(data.size()); i++) {
	            
	            int indexOfEqualSign      = (data.get(i).indexOf("="));
	            int startIndexOfTimeStamp = indexOfEqualSign + 1;
	            int endIndexOfTimeStamp   = indexOfEqualSign + 20;  
	            
	           String atsTimeStamp = data.get(i).substring(startIndexOfTimeStamp,endIndexOfTimeStamp);
	    
	            try {
	          
	                    Date d1 = eachDate.parse(atsTimeStamp);
	                    dateObjects.add(d1);                     
	                }    
	            
	            catch(ParseException pe) {
	                System.out.println("ERROR: could not parse date in string");                        
	                
	            }
	        } 	 
		 
	 }
	  
	 public int ReturnSize() {
		 return dateObjects.size();
	 }
	
	 public String ReturnString() { 
	   return dateObjects.toString();	 

	 }
	
	 public String ReturnElement(int i) {		   
	    return eachDate.format(dateObjects.get(i));
	 
	 }	 
     
	 public Date ReturnDate(int i){
		 
		 return dateObjects.get(i);
	 }
	 
	 
	 public String ReturnCommand(int i) {
		 
		return commands.get(i);	 
			 
	 }
	 
	 
	 public void run() {
				
		 
	 }



}	 

	
		  
		  
	

