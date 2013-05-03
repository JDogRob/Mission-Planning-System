package de.vogella.rc.intro.first;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

import de.jaret.util.date.Interval;
import de.jaret.util.date.JaretDate;
import de.jaret.util.misc.PropertyObservableBase;

/**
 * Simple Implementation of an interval. Listens on property changes on the begin and end date. However it is 
 * better practice to use the JaretDate like an immutable type most of the time.
 * 
 * @author Peter Kliem
 * @version $Id: IntervalImpl.java 845 2009-02-22 18:28:44Z kliem $
 */
public class DeltaVInterval extends PropertyObservableBase implements Interval, PropertyChangeListener {
    /** begin date. */
    protected JaretDate _begin;
    /** end date. */
    protected JaretDate _end;
    /** antenna type. */
    protected String _antennaType;
    
    
    /**
     * Construct a date with begin and end.
     * 
     * @param from begin date (will be copied)
     * @param to end date (will be copied)
     */
    public DeltaVInterval(JaretDate from, JaretDate to) {
        _begin = from.copy();
        _begin.addPropertyChangeListener(this);
        _end = to.copy();
        _end.addPropertyChangeListener(this);
    }

    /**
     * Default constructor leaving the interval uninitialized.
     */
    public DeltaVInterval() {

    }

    /**
     * {@inheritDoc}
     */
    public JaretDate getBegin() {
        return _begin;
    }

    /**
     * {@inheritDoc}
     */
    public JaretDate getEnd() {
        return _end;
    }

    /**
     * {@inheritDoc}
     */
    public void setBegin(JaretDate begin) {
        if (_begin != null) {
            _begin.removePropertyChangeListener(this);
        }
        JaretDate oldVal = _begin;
        _begin = begin;
        _begin.addPropertyChangeListener(this);
        firePropertyChange(PROP_BEGIN, oldVal, begin);
    }

    /**
     * {@inheritDoc}
     */
    public void setEnd(JaretDate end) {
        if (_end != null) {
            _end.removePropertyChangeListener(this);
        }
        JaretDate oldVal = _end;
        _end = end;
        _end.addPropertyChangeListener(this);
        firePropertyChange(PROP_END, oldVal, end);
    }
    
    public void setAntenna(String antenna) {
       _antennaType = antenna;
    }
    
    public String getAntenna() {
        return _antennaType;
     }
    
    
   
    /**
     * {@inheritDoc}
     */
    public boolean contains(JaretDate date) {
        return getBegin().compareTo(date) <= 0 && getEnd().compareTo(date) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Interval interval) {
        return getBegin().compareTo(interval.getBegin())>=0 && getEnd().compareTo(interval.getEnd())>=0;
    }
    
    /**
     * Static helper, check whether a dat eis included in an interval.
     * 
     * @param interval interval
     * @param date date to check for inclusion
     * @return true if date is contained in interval
     */
    public static boolean containsStatic(Interval interval, JaretDate date) {
        return interval.getBegin().compareTo(date) <= 0 && interval.getEnd().compareTo(date) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    public int getSeconds() {
        return getEnd().diffSeconds(getBegin());
     
    }

    public double getMinutes() {
        return getEnd().diffMinutes(getBegin());
     
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
             	
    	return toGMTdisplayString();
   
    }

   
    
    /**
     * {@inheritDoc}
     */
    public boolean intersects(Interval interval) {
        return intersect(this, interval);
    }

    /**
     * Helper method to determine if two intervals intersect.
     * 
     * @param i1 interval1
     * @param i2 interval2
     * @return true if i1 and i2 intersect
     */
    public static boolean intersect(Interval i1, Interval i2) {
        if (i1.contains(i2.getBegin()) || i1.contains(i2.getEnd())) {
            return true;
        }
        if (i2.contains(i1.getBegin()) || i2.contains(i1.getEnd())) {
            return true;
        }
        return false;
    }

    /**
     * Static helper method, check intersection not including the interval edges.
     * 
     * @param i1 interval 1 
     * @param i2 interval 2
     * @return true if the intervals intersect
     */
    public static boolean intersectNonIncluding(Interval i1, Interval i2) {
        if (containsNonIncluding(i1, i2.getBegin()) || containsNonIncluding(i1, i2.getEnd())) {
            return true;
        }
        if (containsNonIncluding(i2, i1.getBegin()) || containsNonIncluding(i2, i1.getEnd())) {
            return true;
        }
        // special case: intervals are exactly the same
        if (i1.getBegin().equals(i2.getBegin()) && i1.getEnd().equals(i2.getEnd())) {
            return true;
        }
        return false;
        
        
        
     
    }

    /**
     * Check whether a given date is contained in the interval not including the exact boundaries.
     * 
     * @param interval interval
     * @param date date to be checked
     * @return true if the date is inside the interval
     */
    public static boolean containsNonIncluding(Interval interval, JaretDate date) {
        return interval.getBegin().compareTo(date) < 0 && interval.getEnd().compareTo(date) > 0;
    }

    /**
     * {@inheritDoc} check begin and end instances.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // instance check is intended!
        if (evt.getSource() == _begin) {
            firePropertyChange(PROP_BEGIN, evt.getOldValue(), evt.getNewValue());
        } else if (evt.getSource() == _end) {
            firePropertyChange(PROP_END, evt.getOldValue(), evt.getNewValue());
        }
    }
    
    
    public String toGMTdisplayString() {
     	
    	String gmtFormatString = "DDD:HH:mm:ss yyyy";
        final SimpleDateFormat gmtDateFormat = new java.text.SimpleDateFormat(gmtFormatString);
        
        String startDate = gmtDateFormat.format(getBegin().getDate());
    	String endDate   = gmtDateFormat.format(getEnd().getDate());
    	
    	return startDate + "--" + endDate;
   
    }
    
    
    

}

