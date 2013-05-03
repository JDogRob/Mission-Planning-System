package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Class shall create a new table for a parsed Automated Time Sequence File.
 * 
 * 1. Takes a TableView as the input
 * 
 * Methods:
 * 1. 
 * 2. Return a table item
 * 3. 
 */

public class TableControl{
    
	int cmdCounter = 0;
	
	/**
	 * Creates a new table and adds it to the composite
	 */
	
	Table table;
	TableColumn column;
	
	TableControl (Composite passedComposite){
	
		ScrolledComposite sc = new ScrolledComposite(passedComposite, SWT.V_SCROLL);
		Composite composite = new Composite(sc, SWT.NONE);
		table = new Table(composite, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		composite.setLayout(gridLayout);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
		
	    table.setRedraw(true);
	
	}
	
	
	public Table getTable(){
		
		return table;
		
	}
	
	public void addColumns (Table table, String columnHeader){
		
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText(columnHeader);
		column.pack();
	}
	
	
	/**
	 * Adds a new TableItem to the end of the table
	 */
	
	public void addTableItem(Table passedTable, String tableItemString, String cmdString) {
		
		cmdCounter = cmdCounter + 1;
	
		String cmdCounterString = Integer.toString(cmdCounter);
		
		TableItem ti = new TableItem(passedTable, SWT.NONE);
		
		ti.setText(0, cmdCounterString);
        ti.setText(1, tableItemString);
        ti.setText(2, cmdString);
      
	}
	
	/**
	 * Returns a TableItems string given the Table and the Integer Column
	 */
	
	public String returnTableItemTextByColumn(final Table table, int columnNumber){
		TableItem ti = new TableItem(table, SWT.NONE);
		return ti.getText(columnNumber);
		
	}
	
	/**
	 * Returns the number of columns in the receiver
	 */
	
	public int returnColumns(Table passedTable) {
		return passedTable.getColumnCount();
			
	}
	
	public TableColumn getColumn(int columnNumber){
		
		return table.getColumn(columnNumber);		
	}
	
	
	public void packColumn(TableColumn column){
		
		column.pack();
		
	}
	
	  
}
	
