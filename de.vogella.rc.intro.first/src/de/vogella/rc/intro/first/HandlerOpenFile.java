package de.vogella.rc.intro.first;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FilenameFilter;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;


public class HandlerOpenFile extends AbstractHandler implements IViewActionDelegate  {
    
	private IViewPart view;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    
		return null;
	    
	  }		 
	public void run(IAction action) {
		
		
		Display.getDefault().asyncExec(new Runnable(){
			
			public void run () {
				
				Frame fileChooserFrame = new Frame();
				FileDialog fc = new FileDialog(fileChooserFrame);
				fc.setFilenameFilter(new FilenameFilter() {
				    public boolean accept(File dir, String fileName) { 
				    		
				    	if (fileName.endsWith(".txt")) return true;
				    	    return false;
				    	}  	
				    
				});
				
				fc.setDirectory("/Users/Jason/Desktop");
			    fc.setMode( FileDialog.LOAD );
			    fc.setVisible(true);
			    
			    String fullPathName = fc.getDirectory() + fc.getFile();
			    
			    CreateModel currentWorkingModel = new CreateModel();
			    
			    currentWorkingModel.Modelcreation(fullPathName);
			    GhanttChartView.setModel(currentWorkingModel.Modelcreation(fullPathName));
			    ActivityTableView.setTable(fullPathName);
	
			}
	
		});
	}
public void selectionChanged(IAction action, ISelection selection) {
	

}

public void init(IViewPart view) {
	this.view = view;
	
}








}
