package de.vogella.rc.intro.first;

//testfasdfasd Part II
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import org.eclipse.ui.part.ViewPart;

import de.jaret.util.date.JaretDate;


public class DigitalClockView extends ViewPart {
	
	boolean isSimulationOffSetApplied = false;
	Calendar simulationOffSetTime;
	long simulationTimeOffsetInMillis;
	long deltaTime;
	
	public DigitalClockView() {
		super();
	}
	
	public boolean isSimulationOffsetApplied() {
		return isSimulationOffSetApplied;
	}
	
	
	public void setSimulationOffset(Calendar offset){
		
		TimeZone localTimeZone = TimeZone.getDefault();
		Calendar local   = new GregorianCalendar(localTimeZone);
		
		simulationOffSetTime = new GregorianCalendar();
		
		isSimulationOffSetApplied = true;
		simulationOffSetTime.set(2013, 05, 15, 03, 27, 15);
		simulationTimeOffsetInMillis = simulationOffSetTime.getTimeInMillis();
		deltaTime = simulationTimeOffsetInMillis - local.getTimeInMillis();
		
	}
	
	public boolean removeSimulationOffset(){
		return isSimulationOffSetApplied = false;
		
	}
	
	public void createPartControl(Composite parent) {
		
		TimeZone tz = TimeZone.getDefault();
		
		Calendar currentTime = new GregorianCalendar(tz);
		setSimulationOffset(currentTime);
		
		/**
		 * Define all groups to be added to the Time Management Tab 
		 */
		
		Group localTimeGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
		localTimeGroup.setText("Local Times");
		
		Group aosTimeGroup = new Group(parent, SWT.SHADOW_NONE);
		aosTimeGroup.setText("Acquisition of Signal");
	
		Group losTimeGroup = new Group(parent, SWT.SHADOW_NONE);
		losTimeGroup.setText("Loss of Signal");
		
		/**
		 * Define the font for the Time Zone Labels
		 */	
		
		Font monoSpacedBoldFont = new Font(parent.getDisplay(), "Courier", 12, SWT.BOLD);
		
		/**
		 * Each Group will have a Grid Layout with two columns
		 */	
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		/**
		 * Apply the Grid Layout to each group
		 */	
		
		localTimeGroup.setLayout(gridLayout);
		aosTimeGroup.setLayout(gridLayout);
		losTimeGroup.setLayout(gridLayout);
		
		/**
		 * Format Strings for SimpleDateFormat
		 */
		
		String estFormatString = "MM/dd/yyyy hh:mm:ss aa";
		String gmtFormatString = "DDD:HH:mm:ss";
		String jscFormatString = "MM/dd/yyyy hh:mm:ss aa";
		
		/**
		 * Grab the current date / time
		 */
		
		Date myDate = new java.util.Date();
		
		/**
		 * Local Times 
		 */	
		
	    final SimpleDateFormat estDateFormat = new java.text.SimpleDateFormat(estFormatString);
	    final SimpleDateFormat gmtDateFormat = new java.text.SimpleDateFormat(gmtFormatString);
	    final SimpleDateFormat jscDateFormat = new java.text.SimpleDateFormat(jscFormatString);
	    
	    /********************************
		 * Time Management Tab
		 * GMT
		 * EDT
		 * JSC 
		 ********************************/	
	    
	    /**
	     * Set time zones
	     */
	    
	    gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    estDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	    jscDateFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	    
	    /**
	     * Determine if in EDT/EST for label assignment
	     */
	    
	    String estOrEDT;
	    
	    boolean isESTCurrentlyInEDT = TimeZone.getTimeZone("America/Chicago").inDaylightTime(myDate);
	    if (isESTCurrentlyInEDT){
	    	estOrEDT = "EDT:";		
	    } else {	
	    	estOrEDT = "EST:";	
	    }
	    
	    final Label gmtTextLabel = new Label(localTimeGroup, SWT.NONE);
	    gmtTextLabel.setText("GMT:");
	    gmtTextLabel.setFont(monoSpacedBoldFont);
	    gmtTextLabel.pack();
	    
	    final Label gmtTimeLabel = new Label(localTimeGroup, SWT.NONE);
	    gmtTimeLabel.setText(gmtDateFormat.format(myDate));
	    gmtTimeLabel.pack();
	    
		final Label estEdtTextLabel = new Label(localTimeGroup, SWT.NONE);
		estEdtTextLabel.setText(estOrEDT);
		estEdtTextLabel.setFont(monoSpacedBoldFont);
	    estEdtTextLabel.pack();
	    
	    final Label estTimeLabel = new Label(localTimeGroup, SWT.NONE);
	    estTimeLabel.setText(estDateFormat.format(myDate));
	    estTimeLabel.pack();
	    
	    final Label jscTextLabel = new Label(localTimeGroup, SWT.NONE);
	    jscTextLabel.setText("JSC:");
	    jscTextLabel.setFont(monoSpacedBoldFont);
	    jscTextLabel.pack();
	    
	    final Label jscTimeLabel = new Label(localTimeGroup, SWT.NONE);
	    jscTimeLabel.setText(jscDateFormat.format(myDate));
	    jscTimeLabel.pack();
	    
	  
	    // Add to AOS Group
	    
	    final Label aosGmtLabel = new Label(aosTimeGroup, SWT.NONE);
	    aosGmtLabel.setText("GMT:");
	    aosGmtLabel.setFont(monoSpacedBoldFont);
	    aosGmtLabel.pack();
	    
	    String aosGmtFormatString = "DDD:HH:mm:ss";
	    final Label aosGMTTimeLabel = new Label (aosTimeGroup, SWT.NONE);
	    final SimpleDateFormat aosGmtFormat = new java.text.SimpleDateFormat(aosGmtFormatString);
	    
	    aosGMTTimeLabel.setText("000:00:00.00");
	    
	    
	    final Label aosLabel = new Label(aosTimeGroup, SWT.NONE);
	    aosLabel.setText("AOS:");
	    aosLabel.setFont(monoSpacedBoldFont);
	    aosLabel.pack();
	    
	    String aosCountdownFormatString = "DDD:HH:mm:ss";
	    final Label aosCountdownTimer = new Label(aosTimeGroup, SWT.NONE);
	    final SimpleDateFormat aosCountdownFormat = new SimpleDateFormat(aosCountdownFormatString);
	    
	    TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
	    
	    aosCountdownFormat.setTimeZone(gmtTimeZone);
	    aosCountdownTimer.setText("000:00:00.00");
	    
	    
	    final Label aosEdt = new Label (aosTimeGroup, SWT.NONE);
	    aosEdt.setText(estOrEDT);
	    aosEdt.setFont(monoSpacedBoldFont);
	    aosEdt.pack();
	    
	    final Label aosEDTTimeLabel = new Label(aosTimeGroup, SWT.NONE);
	    aosEDTTimeLabel.setText(estDateFormat.format(myDate));
	    aosEDTTimeLabel.pack();
	    
	    aosEDTTimeLabel.setText("00/00/0000 00:00:00 AM/PM");
	    
	    /**
	     * Add to the LOS Group
	     */
	    
	    //GMT Time Label
	    
	    final Label losGmtLabel = new Label(losTimeGroup, SWT.NONE);
	    losGmtLabel.setText("GMT:");
	    losGmtLabel.setFont(monoSpacedBoldFont);
	    losGmtLabel.pack();
	    
	    String losGmtFormatString = "DDD:HH:mm:ss";
	    final Label losGMTTimeLabel = new Label (losTimeGroup, SWT.NONE);
	    final SimpleDateFormat losGmtFormat = new java.text.SimpleDateFormat(losGmtFormatString);
	    
	    losGMTTimeLabel.setText("000:00:00.00");
	    
	    // LOS Count down Label
	    
	    final Label losLabel = new Label(losTimeGroup, SWT.NONE);
	    losLabel.setText("LOS:");
	    losLabel.setFont(monoSpacedBoldFont);
	    losLabel.pack();
	    
	    String losCountdownFormatString = "DDD:HH:mm:ss.SSS";
	    final Label losCountdownTimer = new Label(losTimeGroup, SWT.NONE);
	    final SimpleDateFormat losCountdownFormat = new java.text.SimpleDateFormat(losCountdownFormatString);
	    
	    losCountdownTimer.setText("000:00:00.00.000");
	    
	    Job updateTimeLables = new Job("Update Local Time Lables") {
			
			protected IStatus run (IProgressMonitor monitor) {
			
				if (monitor.isCanceled()) return Status.CANCEL_STATUS;
			
				Display.getDefault().asyncExec(new Runnable() {
				
                TimeZone localTimeZone = TimeZone.getDefault();
				TimeZone gmtTimeZone   = TimeZone.getTimeZone("Greenwich");    
				TimeZone centralZone   = TimeZone.getTimeZone("Central");
				
				public void run() {
					
					if(isSimulationOffsetApplied() == false){
						
						Calendar local   = new GregorianCalendar(localTimeZone);
						Calendar gmt     = new GregorianCalendar(gmtTimeZone);
						Calendar central = new GregorianCalendar(centralZone);
						
						estTimeLabel.setText(estDateFormat.format(local.getTime()));
						gmtTimeLabel.setText(gmtDateFormat.format(gmt.getTime()));
						jscTimeLabel.setText(jscDateFormat.format(central.getTime()));	
						
					} else {
						
						Calendar local   = new GregorianCalendar(localTimeZone);
						Calendar gmt     = new GregorianCalendar(gmtTimeZone);
						Calendar central = new GregorianCalendar(centralZone);
						
						gmt.setTimeInMillis(gmt.getTimeInMillis() + deltaTime);
						local.setTimeInMillis(local.getTimeInMillis() + deltaTime);
						central.setTimeInMillis(central.getTimeInMillis() + deltaTime);
						
						estTimeLabel.setText(estDateFormat.format(local.getTimeInMillis()));
						gmtTimeLabel.setText(gmtDateFormat.format(gmt.getTimeInMillis()));
						jscTimeLabel.setText(jscDateFormat.format(central.getTimeInMillis()));
						
					}	
					
				}
				
				});
	    
				schedule(1000);      
				return Status.OK_STATUS;
			}
			
			};
			
			updateTimeLables.setPriority(Job.SHORT);
			updateTimeLables.schedule();
				 
			
		Job updateAOScountDownTimer = new Job("Update AOS Count Down Timer") {
		    protected IStatus run (IProgressMonitor monitor) {
				
				if (monitor.isCanceled()) return Status.CANCEL_STATUS;
			
				Display.getDefault().asyncExec(new Runnable() {
	   	    
				public void run() {
					
					JaretDate jaretDateTimeObject = new JaretDate();			
					
					for (int i = 0; i<TDRSSParser.getPassStartTimeArraySize(); i++) {				
						
							int aosDateIsGreaterThanCurrentDate = TDRSSParser.getPassStartTimeArrayValue(i).compareTo(jaretDateTimeObject);
						
							if (aosDateIsGreaterThanCurrentDate>0){
								
								Calendar currentDate    = Calendar.getInstance();
						        Calendar futureAOSDate  = Calendar.getInstance();
								Calendar countdownTimer = Calendar.getInstance();
						       
								futureAOSDate.setTime(TDRSSParser.getPassStartTimeArrayValue(i).getDate());
								
								 /**
							     * Set the next AOS in GMT and EDT
							     */
								
								aosGMTTimeLabel.setText(gmtDateFormat.format(futureAOSDate.getTime()));
								aosEDTTimeLabel.setText(estDateFormat.format(futureAOSDate.getTime()));
								
								/**
							     * Set the AOS count-down timer
							     */
								
								long deltaTimeInMilliSeconds = futureAOSDate.getTimeInMillis() - currentDate.getTimeInMillis();
								
								
								
								//int seconds = countdownTimer.ge
								//int minutes
								//int hours
								//int days
								
								countdownTimer.setLenient(true);
								countdownTimer.setTimeInMillis(deltaTimeInMilliSeconds);
								
								countdownTimer.set(Calendar.DAY_OF_YEAR, -2);
								
								aosCountdownTimer.setText(aosCountdownFormat.format(countdownTimer.getTime()));
								
								break;    
							}
						} 
					
			
					}
				});
	    
				schedule(1000);     
				return Status.OK_STATUS;
		    }
		    
		    };
	 
		updateAOScountDownTimer.setPriority(Job.LONG);
	    updateAOScountDownTimer.schedule();
		 	 
	
	
	Job updateLOScountDownTimer = new Job("Update LOS Count Down Timer") {
	    protected IStatus run (IProgressMonitor monitor) {
			
			if (monitor.isCanceled()) return Status.CANCEL_STATUS;
		
			Display.getDefault().asyncExec(new Runnable() {
   	    
			public void run() {
				
				JaretDate jaretDateTimeObject = new JaretDate();			

				for (int i = 0; i<TDRSSParser.getPassEndTimeArraySize(); i++) {				
					
						int losDateIsGreaterThanCurrentDate = TDRSSParser.getPassEndTimeArrayValue(i).compareDateTo(jaretDateTimeObject);
					
						if (losDateIsGreaterThanCurrentDate>0){
							
							Calendar currentDate    = Calendar.getInstance();
					        Calendar futureLOSDate  = Calendar.getInstance();
							Calendar countdownTimer = Calendar.getInstance();
					       
							futureLOSDate.setTime(TDRSSParser.getPassEndTimeArrayValue(i).getDate());
							
							 /**
						     * Set the next LOS in GMT
						     */
							
							losGMTTimeLabel.setText(gmtDateFormat.format(futureLOSDate.getTime()));							
	
							//losEDTTimeLabel.setText(estDateFormat.format(futureLOSDate.getTime()));
							
							long deltaTimeInMilliSeconds = futureLOSDate.getTimeInMillis() - currentDate.getTimeInMillis();
							countdownTimer.setTimeInMillis(deltaTimeInMilliSeconds);
							
							losCountdownTimer.setText(aosCountdownFormat.format(countdownTimer.getTime()));
							
							break;    
						}
					} 
				
		
				}
			});
    
			schedule(1000);     
			return Status.OK_STATUS;
	    }
	    
	    };
 
	updateLOScountDownTimer.setPriority(Job.LONG);
    updateLOScountDownTimer.schedule();
	 	 
}
	
	
	
	
	
	@Override
	public void setFocus() {
		

	}

}




	