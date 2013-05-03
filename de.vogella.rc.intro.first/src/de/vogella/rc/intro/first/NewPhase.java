package de.vogella.rc.intro.first;

import java.beans.PropertyChangeSupport;
public class NewPhase extends NewPhaseModel {

	private static int counter = 0;
	private int id;
	private String phaseName;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	public NewPhase(String phaseName){
		id = counter++;
		this.phaseName = phaseName;
		setPhaseName(phaseName);
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getPhaseName(){
		return this.phaseName;
	}
	
	
	public void setPhaseName(String phaseName){
		firePropertyChange("phaseName", this.phaseName, this.phaseName = phaseName);
		
	}
	
	
	public String toString(){
		return phaseName;
	}
	
	public boolean equals(Object obj){
			return true;	
	}
	
}
