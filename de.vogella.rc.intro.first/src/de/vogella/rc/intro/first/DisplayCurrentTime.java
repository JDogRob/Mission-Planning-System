package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayCurrentTime implements Runnable {
	
	DateFormat atsFormat = new  SimpleDateFormat ("yyyy/MM:dd:HH:mm:ss");
	private ATSBuffer1View viewPart;
	
	public DisplayCurrentTime(ATSBuffer1View viewPart){  
		this.viewPart = viewPart;  
		}  
	
	public void go(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		 while (viewPart != null)  {  
		     Date date = new Date();
		     atsFormat.format(date);
		    // viewPart.updateLabelText(atsFormat.format(date));
	       
		     try { 
	    	  
                 // Wait 500milliseconds before continuing 
                Thread.sleep(1000); 
                
	    	   } 
           
	      catch (InterruptedException e) { 
                System.out.println(e); 
           } 
	   }	
   }
}
