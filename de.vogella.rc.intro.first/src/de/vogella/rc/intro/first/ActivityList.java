package de.vogella.rc.intro.first;

import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IStaleListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.jface.databinding.swt.WidgetProperties;


import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.*;

public class ActivityList extends ViewPart implements IObservableValue{
	
	
	public static final String ID = "robinson.activitylist";
	private static int counter = 0;
	static int id;
	NewPhase phase;
	Tree taskTree;
	
	
	@Override
	public void createPartControl(Composite parent) {
		
		Composite viewParent = parent.getParent().getParent().getParent();
		
		StackLayout stackLayout = (StackLayout) viewParent.getLayout();
		
		stackLayout.marginHeight = -1;
		stackLayout.marginWidth = -1;
		
		final CTabFolder activityListFolder = new CTabFolder(parent, SWT.TOP);
		
		activityListFolder.setSimple(false);
		activityListFolder.setBorderVisible(true);
		activityListFolder.setSingle(false);
		activityListFolder.setLayoutData(new GridData(GridData.FILL_BOTH));	
		activityListFolder.setMinimizeVisible(true);
		activityListFolder.setMaximizeVisible(true);
		
		Display display = Display.getCurrent();
		int colorCount = 3;
		Color[] colors = new Color[colorCount];
		
		colors [0] = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
		colors [1] = display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
		colors [2] = colors [0];
		
		
		int[] percents = new int[colorCount - 1];
		percents[0] = 20;
		percents[1] = 50;
		
		activityListFolder.setSelectionBackground(colors, percents, true);
		activityListFolder.setSelectionForeground(display.getSystemColor(SWT.COLOR_TITLE_FOREGROUND));
		
		CTabItem item = new CTabItem( activityListFolder, SWT.NONE );
		item.setText( "Planning   " );
		
		activityListFolder.setSelection(item);
		
		activityListFolder.showItem(item);
		URL iconUrl = FileLocator.find(Platform.getBundle("de.vogell.rcp.intro.first"), new Path("icons/taskListIcon.png"), null);
		
		Image toDoListImage = ImageDescriptor.createFromURL(iconUrl).createImage();
		item.setImage(toDoListImage);
		
		
		Rectangle folderBounds = activityListFolder.getClientArea();
		
		
		final ToolBar newTaskToolBar = new ToolBar(activityListFolder, SWT.WRAP);
		
		newTaskToolBar.setBounds(0, 0, folderBounds.width, folderBounds.height);
		
		newTaskToolBar.setLocation(500, 0);
		
		activityListFolder.addListener(SWT.Resize, new Listener() {
		      public void handleEvent(Event e) {
		        Rectangle rect = activityListFolder.getClientArea();
		        Point size = newTaskToolBar.computeSize(rect.width, SWT.DEFAULT);
		        newTaskToolBar.setSize(size);
		      }
		    });
		
		
		final ToolItem newTaskToolItem = new ToolItem(newTaskToolBar, SWT.PUSH);
	    new ToolItem(newTaskToolBar, SWT.SEPARATOR); 
		//Point size = activityListFolder.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		
		
		newTaskToolItem.setImage(toDoListImage);
		
		newTaskToolItem.setToolTipText("Define New Task");
		
		newTaskToolBar.setOrientation(SWT.LEFT_TO_RIGHT);
		
		newTaskToolBar.pack();
		
	  
	    //Add the tree
		
		taskTree = new Tree(activityListFolder, SWT.MULTI);
		item.setControl(taskTree);
		
		
		final TreeItem firstItem = new TreeItem(taskTree, 0);
		firstItem.setText("Mission Phases");
		firstItem.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Menu missionPhaseMenu = new Menu(taskTree);
		MenuItem newMissionPhase = new MenuItem(missionPhaseMenu, SWT.CASCADE);
		newMissionPhase.setText("New");
		
		Menu newMenu = new Menu(newMissionPhase);
		MenuItem newPhase = new MenuItem(newMenu, SWT.PUSH);
		
		newPhase.setText("New Phase... ");
		
		newPhase.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event){
				addMissionPhase(firstItem);
			}
			
		});
		
		
		newMissionPhase.setMenu(newMenu);
		taskTree.setMenu(missionPhaseMenu);
			
		/*
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		DragSource source = new DragSource(dragLabel, operations);
	
		Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
		source.setTransfer(types);
			 
			source.addDragListener(new DragSourceListener() {
			   public void dragStart(DragSourceEvent event) {
			      // Only start the drag if there is actually text in the
			      // label - this text will be what is dropped on the target.
			      if (dragLabel.getText().length() == 0) {
			          event.doit = false;
			      }
			   }
			   public void dragSetData(DragSourceEvent event) {
			     // Provide the data of the requested type.
			     if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			          event.data = dragLabel.getText();
			     }
			   }
			   public void dragFinished(DragSourceEvent event) {
			     // If a move operation has been performed, remove the data
			     // from the source
			     if (event.detail == DND.DROP_MOVE)
			         dragLabel.setText("");
			     }
			   
	   });
	
	*/
		
		//DataBindingContext ctx = new DataBindingContext();
		//IObservableValue target = WidgetProperties.text(SWT.Modify).observe(newMissionPhase);
		
		
	}

	
	
	public void addMissionPhase(TreeItem newMissionPhaseItem){
		
		IWorkbenchPage page;	
		page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
		try {
			
			PropertyChangeListener listener;
			
			page.openEditor(new NewPhaseEditorInput(id), "robinson.jason.new.phase.editor");	
			NewPhase phase = new NewPhase("New Mission Phase");
		
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		ArrayList<TreeItem> missionPhaseTreeItems = new ArrayList<TreeItem>();
		
		TreeItem item = new TreeItem(newMissionPhaseItem, 0);
		
		item.setText("New Mission Phase");
		missionPhaseTreeItems.add(item);
		
		DataBindingContext ctx = new DataBindingContext();
		
		//IObservableValue target = WidgetProperties.text(SWT.Modify).observe(item);
		
		//IObservableValue model = BeanProperties.value(NewPhase.class, "phaseName").observe(phase.getPhaseName());
		
		//ctx.bindValue(target, model);
		
	}
	
	
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}



	@Override
	public Realm getRealm() {
		 //TODO Auto-generated method stub
		return null;
	}



	@Override
	public void addChangeListener(IChangeListener listener) {
		 //TODO Auto-generated method stub
		
	}


    
	@Override
	public void removeChangeListener(IChangeListener listener) {
		 //TODO Auto-generated method stub
		
	}



	@Override
	public void addStaleListener(IStaleListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeStaleListener(IStaleListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isStale() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void addDisposeListener(IDisposeListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeDisposeListener(IDisposeListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Object getValueType() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addValueChangeListener(IValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeValueChangeListener(IValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
