package de.vogella.rc.intro.first;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ATSBuffer2View extends ViewPart {

	public ATSBuffer2View() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		
		TableControl mc = new TableControl(parent);
		
		mc.addColumns(mc.getTable(), "Index");
		mc.addColumns(mc.getTable(), "Execution Time");
		mc.addColumns(mc.getTable(), "Command");
		
		ParseMissionPlanningAutomatedTimeSequenceFile ParsedFile = new ParseMissionPlanningAutomatedTimeSequenceFile();
	       
		ParsedFile.OpenFile();
		ParsedFile.ParseDates();		 
		ParsedFile.ParseCommands();
		
		int arraySize = ParsedFile.ReturnSize();
		
		for (int i=0; i<(arraySize); i++) {
			mc.addTableItem(mc.getTable(), ParsedFile.ReturnElement(i).toString(), ParsedFile.ReturnCommand(i).toString());
	}

		DisplayTime ms = new DisplayTime();
		
		mc.packColumn(mc.getColumn(0));
		mc.packColumn(mc.getColumn(1));
		mc.packColumn(mc.getColumn(2));
		
		ms.stepThroughTable(mc.getTable());
	}		
		
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
