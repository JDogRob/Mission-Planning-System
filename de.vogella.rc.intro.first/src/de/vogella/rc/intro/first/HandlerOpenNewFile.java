package de.vogella.rc.intro.first;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class HandlerOpenNewFile extends AbstractHandler implements IHandler {

	static String newFileToLoad;
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
       Display.getDefault().asyncExec(new Runnable(){
			
		public void run () {
				
				Shell fileChooserFrame = new Shell();
				FileDialog fc = new FileDialog(fileChooserFrame);
				fc.setText("Select File");
				final String newFileToLoad = fc.open();
			
			    CreateModel_TDRSS tdrssModel = new CreateModel_TDRSS();
			    GhanttChartView.setModel(tdrssModel.Modelcreation(newFileToLoad));
			          
			}
	
		});
	
		return null;
	}

}
