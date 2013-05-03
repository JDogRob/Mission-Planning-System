package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

public class TimeJob {
   
	
	DateFormat atsFormat = new  SimpleDateFormat ("yyyy/MM:dd:HH:mm:ss");
	
	
	public void Sweep(final Table passedTable){
		 
		
		 Job sweepAndHighlight = new Job("Sweep Job") {
			
			 protected IStatus run (IProgressMonitor monitor) {
				 
				 Display.getDefault().asyncExec(new Runnable() {
					public void run(){ 
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
				        
		    			   returnDate = atsFormat.parse(passedTable.getItem(counter).getText(1));
					    
		    			   System.out.println(returnDate);
					    
		    		   } catch (ParseException e1) {
						
		    			   e1.printStackTrace();
		    		   }
	                   
		    		   finally {
		    		    System.out.println(atsFormat.format(date));
	   
		    		
		    			  schedule(1000);     
		    		   }
		    
	
				 	}

			 }
	
		 
		});
		 
				 return Status.OK_STATUS; //return whatever status based on your job success. 
	}
				
		 };

sweepAndHighlight.setPriority(Job.LONG);
sweepAndHighlight.schedule();

	}
}
	