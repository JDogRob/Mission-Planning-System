package de.vogella.rc.intro.first;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class NewPhaseEditor extends EditorPart implements ModifyListener{
	
	public static final String ID = "robinson.jason.new.phase.editor";
	
	private NewPhaseEditorInput input;
	private NewPhase newPhase;
	private boolean isEditorDirty = false;
	private Text text;
	private Label label1;
	
	public NewPhaseEditor() {
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
	
		NewPhaseEditorInput new_name = (NewPhaseEditorInput) input;
		this.input = (NewPhaseEditorInput) input;
		setSite(site);
		setInput(input);
		setPartName("New Phase");
		
	}

	@Override
	public boolean isDirty() {
		return isEditorDirty;
	}

	public void dirtyStateChanged() {
		isEditorDirty = true;
		this.firePropertyChange(PROP_INPUT);
		firePropertyChange(PROP_DIRTY);
		newPhase.setPhaseName(label1.getText());
	}
	
	
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void createPartControl(Composite parent) {
		
		GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    parent.setLayout(layout);
	   
	    label1 = new Label(parent, SWT.NONE);
	    label1.setText("Phase Name:");
	    text = new Text(parent, SWT.BORDER);
	    text.setText("Mission Phase");
	    text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	    
	    text.addModifyListener(this);
	    
	} 

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyText(ModifyEvent e) {
		dirtyStateChanged();	
	}
}
