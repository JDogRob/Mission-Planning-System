package de.vogella.rc.intro.first;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

public class ActivityTableView extends ViewPart {	
	
	static ParseOrbitEventsFile tableTreeView;
	static Tree tree; 
	
	
	public void createPartControl(Composite parent) {
		
		tree = new Tree(parent, SWT.VIRTUAL | SWT.BORDER);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
		column1.setText("Event Time");
		column1.setWidth(200);
		
		TreeColumn column2 = new TreeColumn(tree, SWT.LEFT);
		column2.setText("AOS/LOS");
		column2.setWidth(200);
		
		TreeColumn column3 = new TreeColumn(tree, SWT.LEFT);
		column3.setText("Event");
		column3.setWidth(200);
			
		
	}
	
	
	
	public static void setTable(String inputFile){

		ParseOrbitEventsFile tableTreeView = new ParseOrbitEventsFile(inputFile);
		
		tableTreeView.OpenFile();
		tableTreeView.parseElements();
		
		boolean aosTrue = false;
		boolean losTrue = false;
		
		for (int i=0; i < tableTreeView.getElementTimeArraySize(); i++){
				
			    TreeItem aosItem = null;
				TreeItem losItem;
				TreeItem eventItem;
				
				if (tableTreeView.getElementNameArrayValue(i).contains("AOS") && !aosTrue){
			
					aosTrue = true;
					
					aosItem = new TreeItem(tree, 1);
					
					aosItem.setText(new String[]{tableTreeView.getElementTimeArrayValue(i).toDisplayString(),
											  tableTreeView.getElementNameArrayValue(i)});
				}
				
				if ((tableTreeView.getElementNameArrayValue(i).contains("LOS") && aosTrue)){
				    
					losTrue = true;	
				}
				
				
				if (aosTrue && losTrue){
				
					losItem  = new TreeItem(tree, 1);
					
					losItem.setText(new String[]{tableTreeView.getElementTimeArrayValue(i).toDisplayString(),
							  tableTreeView.getElementNameArrayValue(i)});
					
					aosTrue = false;
					losTrue = false;	
				}
				
				
				else if (tableTreeView.getElementNameArrayValue(i).contains("null")) {
					
					
					
				}
				
				
				else if (tableTreeView.getElementNameArrayValue(i).contains("DownlinkRateChange")){
					
					
					
				}
				
				
				else if (tableTreeView.getElementNameArrayValue(i).contains("DataRateChange")){
					
					
					
				}
				
				else {
					
					eventItem = new TreeItem(tree, 1);
					eventItem.setText(new String[]{tableTreeView.getElementTimeArrayValue(i).toDisplayString(),"",
							  tableTreeView.getElementNameArrayValue(i)});
					
				}
				
			}
			
			
			
		}
	
	
	

	public void setFocus() {
		

	}

}
