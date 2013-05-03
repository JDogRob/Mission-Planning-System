package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

public class TimeFlow {

	DateFormat atsFormat = new  SimpleDateFormat ("yyyy/MM:dd:HH:mm:ss");
	
	public void stepThroughTableItems(final Table passedTable) {
		int counter = 0; 
		int  numberOfRows = passedTable.getItemCount();
 	   	Date returnDate;
		
 	   	while (counter != numberOfRows ) {  
		    
 	   		// This counter steps through the tableItems
 	   		counter = counter + 1; 
			
 	   		// Continually grab new Date and Time 
 	   		Date date = new Date();
			
 	   		// Format that Date and Time into what we see in the ATS
 	   		atsFormat.format(date);
		    
 	   		// Extract date from table and attempt to parse
 	   		try {
		        
 	   				// String StringDate = passedTable.getItem(counter).getText(1);
 	   				returnDate = atsFormat.parse(passedTable.getItem(counter).getText(1));
			    
 	   				System.out.println(returnDate);
			    
 	   			} catch (ParseException e1) {
				
 	   				e1.printStackTrace();
 	   			}
		     
 	   		System.out.println(atsFormat.format(date));
		   
 		   try { 
 			   Thread.sleep(1000);     
 		   } catch (InterruptedException e) { 
            
 		System.out.println(e); 
        
 		} 
		
		}

}

	
	
	public void updateUI(final Table passedTable){
		Display.getDefault().asyncExec(new Runnable() {

	    	   public void run() {
	    	           
	
	    	    	   
	    	    	   int counter = 0; 
	    	    	   int  numberOfRows = passedTable.getItemCount();
	    	    	   Date returnDate;
	    			
	    	    	   while (counter != numberOfRows ) {  
	    			    
	    	    		   // This counter steps through the tableItems
	    	    		   counter = counter + 1; 
	    				
	    	    		   // Continually grab new Date and Time 
	    	    		   Date date = new Date();
	    				
	    	    		   // Format that Date and Time into what we see in the ATS
	    	    		   atsFormat.format(date);
	    			    
	    	    		   // Extract date from table and attempt to parse
	    	    		   try {
	    			        
	    	    			  // String StringDate = passedTable.getItem(counter).getText(1);
	    	    			   returnDate = atsFormat.parse(passedTable.getItem(counter).getText(1));
	    				    
	    	    			   System.out.println(returnDate);
	    				    
	    	    		   } catch (ParseException e1) {
	    					
	    	    			   e1.printStackTrace();
	    	    		   }
	    			     
	    	    		   System.out.println(atsFormat.format(date));
	    			   
	    	    		   try { 
	    	    			   Thread.sleep(1000);     
	    	    		   } catch (InterruptedException e) { 
	    	               
	    	    		System.out.println(e); 
	    	           
	    	    		} 
	    			
	    			}
	    	
	    	   }

		});
	    	   }
      
}

