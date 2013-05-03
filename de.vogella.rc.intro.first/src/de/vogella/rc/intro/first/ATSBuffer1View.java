package de.vogella.rc.intro.first;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Menu;

import de.jaret.util.date.JaretDate;

import java.util.Iterator;

public class ATSBuffer1View extends ViewPart {
	
	public ATSBuffer1View() {
	
	}	

	Menu fileMenu;
	Menu helpMenu;
	
	public void createPartControl(Composite parent) {	
		
		TableControl tc = new TableControl(parent);
		
		tc.addColumns(tc.getTable(), "Index");
		tc.addColumns(tc.getTable(), "Execution Time");
		tc.addColumns(tc.getTable(), "Command");
		
		ParseMissionPlanningAutomatedTimeSequenceFile ParsedFile = new ParseMissionPlanningAutomatedTimeSequenceFile();
  		
		ParsedFile.OpenFile();
		ParsedFile.ParseDates();		 
		ParsedFile.ParseCommands();
		
		int arraySize = ParsedFile.ReturnSize();
	    
		
		for (int i=0; i<(arraySize); i++) {
			
			tc.addTableItem(tc.getTable(), ParsedFile.ReturnElement(i).toString(), ParsedFile.ReturnCommand(i));
			 	   
		}
		
		
		DisplayTime ts = new DisplayTime();
		
		tc.packColumn(tc.getColumn(0));
		tc.packColumn(tc.getColumn(1));
		tc.packColumn(tc.getColumn(2));
		
		
		
		ts.stepThroughTable(tc.getTable());
		
		
	}
	
	


	public void setFocus() {
		
	}

	
	
}
