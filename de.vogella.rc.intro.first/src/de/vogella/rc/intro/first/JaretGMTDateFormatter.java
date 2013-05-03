package de.vogella.rc.intro.first;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import de.jaret.util.date.JaretDate;
import de.jaret.util.date.JaretDateFormatter;

public class JaretGMTDateFormatter extends JaretDateFormatter{
	
	private DateFormat format;
	
	public String toDisplayStringTime(JaretDate date) {
        if (format == null) {
        	format = new  SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
        	format.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        
            return format.format(date.getDate());
        }
	
}
