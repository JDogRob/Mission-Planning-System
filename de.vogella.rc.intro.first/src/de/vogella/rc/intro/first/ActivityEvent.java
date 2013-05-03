
package de.vogella.rc.intro.first;

import de.jaret.util.date.Event;
import de.jaret.util.date.JaretDate;

public class ActivityEvent extends Event {

String _label;

    
    public ActivityEvent(JaretDate date) {
        super(date);
    }

    
    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        String oldVal = _label;
        _label = label;
        
    }
       
}

