package de.vogella.rc.intro.first;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class GhanttChartViewToolBarOpenFile implements IViewActionDelegate {
	
	private IViewPart view;
	
	public void run(IAction action) {
			
			Shell shell = new Shell(Display.getDefault());
			FileDialog fc = new FileDialog(shell, SWT.OPEN);
			
			String filePath = fc.open();
			
			CreateModel newModel = new CreateModel();
		    
			GhanttChartView.setModel(newModel.Modelcreation(filePath));
			GhanttChartView.setMarker();
			
			ActivityTableView.setTable(filePath);
			

			
	}
	
	
	
	public void selectionChanged(IAction action, ISelection selection) {
		

	}

	public void init(IViewPart view) {
		this.view = view;
		
	}

}
