package de.vogella.rc.intro.first;


import de.jaret.util.ui.timebars.model.DefaultRowHeader;
import de.jaret.util.ui.timebars.model.DefaultTimeBarModel;
import de.jaret.util.ui.timebars.model.DefaultTimeBarRowModel;
import de.jaret.util.ui.timebars.model.TimeBarModel;

public class CreateModel_TDRSS {
		
	protected static DefaultTimeBarModel ghantChartModel = new DefaultTimeBarModel();
	
	public TimeBarModel Modelcreation(String inputfile) {
		
		DefaultRowHeader rowHeader = new DefaultRowHeader("Pass Schedule");
		DefaultTimeBarRowModel rowTBR = new DefaultTimeBarRowModel(rowHeader);
		
		TDRSSParser tdrssFile = new TDRSSParser(inputfile);
		
		tdrssFile.OpenFile();
		tdrssFile.parseElements();
		
		TDRSSInterval aosLosInterval   = new TDRSSInterval();
		USNInterval usnAosLosInterval  = new USNInterval();
		
		for (int i = 0; i<TDRSSParser.getPassStartTimeArraySize(); i++) {
			
			
			/**
			 * AOS/LOS 
			 */			
			
			if (TDRSSParser.getPassTypeArrayValue(i).contains("USN") || TDRSSParser.getPassTypeArrayValue(i).contains("NEN") ){
				
				usnAosLosInterval = new USNInterval();
				usnAosLosInterval.setBegin(TDRSSParser.getPassStartTimeArrayValue(i));
				usnAosLosInterval.setEnd(TDRSSParser.getPassEndTimeArrayValue(i));
				rowTBR.addInterval(usnAosLosInterval);
			}
			
			if (TDRSSParser.getPassTypeArrayValue(i).contains("TDRSS")){
			
				aosLosInterval = new TDRSSInterval();
				aosLosInterval.setBegin(TDRSSParser.getPassStartTimeArrayValue(i));
				aosLosInterval.setEnd(TDRSSParser.getPassEndTimeArrayValue(i));	
				aosLosInterval.setAntenna(TDRSSParser.getPassAntennaArrayValue(i));
				rowTBR.addInterval(aosLosInterval);
			}
			
		}
		
		ghantChartModel.addRow(-1,rowTBR);
		return ghantChartModel;
	
	}
	
}
	
