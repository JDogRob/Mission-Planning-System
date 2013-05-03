package de.vogella.rc.intro.first;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import de.jaret.util.date.Event;
import de.jaret.util.date.Interval;
import de.jaret.util.date.JaretDate;
import de.jaret.util.ui.timebars.TimeBarMarkerImpl;
import de.jaret.util.ui.timebars.mod.DefaultIntervalModificator;
import de.jaret.util.ui.timebars.model.TimeBarModel;
import de.jaret.util.ui.timebars.model.TimeBarRow;
import de.jaret.util.ui.timebars.swt.TimeBarViewer;
import de.jaret.util.ui.timebars.swt.renderer.BoxTimeScaleRenderer;
import de.jaret.util.ui.timebars.swt.renderer.CombiningTimeScaleRenderer;
import de.jaret.util.ui.timebars.swt.renderer.DefaultGapRenderer;
import de.jaret.examples.timebars.fancy.swt.renderer.FancyEventRenderer;
import de.jaret.examples.timebars.pdi.swt.SwtControlPanel;
import de.jaret.examples.timebars.simple.OtherIntervalImpl;
import de.jaret.examples.timebars.simple.swt.OverlapControlPanel;

public class GhanttChartView extends ViewPart {
	
	public static final String ID = "robinson.jason.new.phase.editor";
	static TimeBarMarkerImpl marker;
	
private static TimeBarViewer _tbv;
private static TimeBarViewer _dv;

	public static void setModel(TimeBarModel model){
		_tbv.setModel(model);	
	}

	public static void setMarker() {
		
		 Job updateMarker = new Job("Update Marker") {
		
			protected IStatus run (IProgressMonitor monitor) {
			
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				
				Display.getDefault().syncExec( new Runnable() {
			
					public void run() {
						
						JaretDate currentTime = new JaretDate();
						marker = new TimeBarMarkerImpl(false, currentTime);	
						marker.setDate(currentTime);
						
						_tbv.addMarker(marker);
						
						
						
					}
				
		});
				
	
			schedule(10000);
			return Status.OK_STATUS;
			
		}
		
		};
	    
		 final Job remMarker = new Job("Remove Marker") {
				
				protected IStatus run (IProgressMonitor monitor) {
				
					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}
			
					Display.getDefault().syncExec( new Runnable() {
						
						public void run() {
							
							_tbv.remMarker(marker);
						}
					
			});
					
		
				schedule(5000);     
				return Status.OK_STATUS;
				
			}
			
			};
		
		updateMarker.setPriority(Job.INTERACTIVE);
		updateMarker.schedule();
		remMarker.setPriority(Job.INTERACTIVE);
		remMarker.schedule();
	}
	
	
	
	
	

	public void createPartControl(Composite parent) {
		
		setMarker();
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		parent.setLayout(gridLayout);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		
		_dv = new TimeBarViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		_dv.setLayoutData(gd);
		
		_dv.setTitle("2nd TBV");
		
		_tbv = new TimeBarViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		_tbv.setLayoutData(gd);
		
		_tbv.setTimeScalePosition(TimeBarViewer.TIMESCALE_POSITION_TOP);
		
		_tbv.setPixelPerSecond(50);
        _tbv.setDrawRowGrid(true);
		
        _tbv.setTitle("Schedule Viewer");
        _tbv.setGapRenderer(new DefaultGapRenderer());
        
        _tbv.setSecondsDisplayed(86400, true);
        _tbv.setScrollOnFocus(true);
       
        _tbv.setAdjustMinMaxDatesByModel(true);
   
       
        _tbv.addIntervalModificator(new DefaultIntervalModificator());
        
     
        /********************************
		* Register the event classes
		 ********************************/
       
        _tbv.registerTimeBarRenderer(Event.class, new FancyEventRenderer());
        _tbv.registerTimeBarRenderer(DeltaVInterval.class, new DeltaVRenderer());
        _tbv.registerTimeBarRenderer(TDRSSInterval.class, new TDRSSRenderer());
        _tbv.registerTimeBarRenderer(EclipseInterval.class, new EclipseRenderer());
        _tbv.registerTimeBarRenderer(USNInterval.class, new USNRenderer());
        
        
        SwtControlPanel ctrl = new SwtControlPanel(parent, SWT.NULL, _tbv, null);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        ctrl.setLayoutData(gd);
        
        OverlapControlPanel ctrl2 = new OverlapControlPanel(parent, SWT.NULL, _tbv);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        ctrl2.setLayoutData(gd);
         
        BoxTimeScaleRenderer btsr = new BoxTimeScaleRenderer();
        // enable DST correction
        //btsr.setCorrectDST(true);
        
        GMTDateStripRenderer dsr = new GMTDateStripRenderer();
        CombiningTimeScaleRenderer ctsr = new CombiningTimeScaleRenderer(dsr, btsr);
        
        _tbv.setTimeScaleRenderer(ctsr);
        
        _tbv.addIntervalModificator(new DefaultIntervalModificator() {
            @Override
            public double getSecondGridSnap() {
                return 1000;
            }
 
//            @Override
//            public double getSecondGridSnap(TimeBarRow row, Interval interval) {
//            	if (row.getIntervals().size() == 1) {
//            		return 2000;
//            	} 
//            	return -1;
//            }

            @Override
            public boolean isApplicable(TimeBarRow row, Interval interval) {
                return !(interval instanceof OtherIntervalImpl);
            }

        });
        
        _tbv.setDrawRowGrid(true);
        
	}
	
	
	
	
	public void setFocus() {
		

	}


}
