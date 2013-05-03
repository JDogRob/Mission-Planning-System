package de.vogella.rc.intro.first;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;


public class NewPhaseEditorInput implements IEditorInput{

	private final int id;
	
	public NewPhaseEditorInput(int id){
		this.id = id; 
	}
	
	public int getId(){
		return id;
	}
	
	
	public boolean exists() {
		
		return true;
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		
		return null;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		
		return null;
	}


	@Override
	public String getName() {
		
		return String.valueOf(id);
	}

	@Override
	public IPersistableElement getPersistable() {
		
		return null;
	}

	@Override
	public String getToolTipText() {
		
		return "Define a new Mission Phase";
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
	}

	
}
