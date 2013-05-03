package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

public class DisplayTime {
    
	public void stepThroughTable(final Table passedTable){
		
		Job pullDateTimeFromTable = new Job("Sweep Table") {
			
			DateFormat atsFormat = new  SimpleDateFormat ("yyyy/MM:dd:HH:mm:ss");
			int counter = 1;
			Date returnDate;
			Device display = Display.getCurrent();
			
			protected IStatus run (IProgressMonitor monitor) {
				if (monitor.isCanceled()) return Status.CANCEL_STATUS;
				
				
	
	    		// Extract date from table and attempt to parse
	    		Display.getDefault().asyncExec(new Runnable() {
	    			public void run(){ 
	    				
	    				// Grab current date/time for comparison against tableItem date/time
	    				Date date = new Date();
	    				
	    				// Format that Date and Time into what we see in the ATS
	    	    		atsFormat.format(date);
	    				
	    				try {
	    					
	    					// Date of the TableItem
	    					returnDate = atsFormat.parse(passedTable.getItem(counter).getText(1));
	    					
	    					// The initial background color to return to
	    					Color initialbackground = passedTable.getItem(counter).getBackground();
	    					
	    					if (date.before(returnDate)) {
	    						
	    						passedTable.getItem(counter).setBackground(new Color(display, 150, 123, 100));
	    						
	    						if (counter != 0) {
	    							passedTable.getItem(counter - 1).setBackground(initialbackground);
	    						}
	    						
	    						
	    						System.out.println(atsFormat.format(returnDate));
		    					System.out.println(atsFormat.format(date));
	    						
		    					// This counter steps through the tableItems
		    					counter = counter + 1; 
		    					
	    						}
	    					
	    					
	    					}
	    				
	    				catch (ParseException e1) {
							
			    			   e1.printStackTrace();
			    		   }
	    				
	    			}
	    		});
	    	   
	    		
	    		schedule(100);     
	    		 
	    		   
				return Status.OK_STATUS;
				
			}
			
			
		};
		
		pullDateTimeFromTable.setPriority(Job.LONG);
		pullDateTimeFromTable.schedule();
		
	}
	
	
	public void showTime(){
		
		Job sweepAndHighlight = new Job("Sweep Job") {
			
			 protected IStatus run (IProgressMonitor monitor) {
				 
				     if (monitor.isCanceled()) return Status.CANCEL_STATUS;
					 Date date = new Date();
					 System.out.println(date);
					 schedule(1000);
					 return Status.OK_STATUS;
					 			 
		
	          }
	
		};

		sweepAndHighlight.setPriority(Job.LONG);
		sweepAndHighlight.schedule();
		
	}
}
