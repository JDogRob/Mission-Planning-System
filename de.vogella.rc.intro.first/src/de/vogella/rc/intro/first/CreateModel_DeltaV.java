package de.vogella.rc.intro.first;

import de.jaret.examples.timebars.fancy.model.FancyEvent;
import de.jaret.util.date.JaretDate;
import de.jaret.util.ui.timebars.model.DefaultRowHeader;
import de.jaret.util.ui.timebars.model.DefaultTimeBarModel;
import de.jaret.util.ui.timebars.model.DefaultTimeBarRowModel;
import de.jaret.util.ui.timebars.model.TimeBarModel;

public class CreateModel_DeltaV {
	
	protected static DefaultTimeBarModel ghantChartModel = new DefaultTimeBarModel();
	
	public TimeBarModel Modelcreation(String inputfile) {
		
		DefaultRowHeader rowHeader = new DefaultRowHeader("Delta V");
		DefaultTimeBarRowModel rowTBR = new DefaultTimeBarRowModel(rowHeader);
      
		DeltaVParser deltaVFile = new DeltaVParser(inputfile);
		
		deltaVFile.OpenFile();
		deltaVFile.parseElements();
		
		
		for (int i = 0; i<DeltaVParser.getEventItemArraySize(); i++) {
	
			/**
			 * Delta V Start and Stop Types 
			 */			
			
			if ((DeltaVParser.getEventItemArrayValue(i).contains("Start")) && (DeltaVParser.getEventItemArrayValue(i).startsWith("DV")) 
				|| DeltaVParser.getEventItemArrayValue(i).contains("Plane") && (DeltaVParser.getEventItemArrayValue(i).contains("Start"))){
				
				FancyEvent deltaVStartEvent = new FancyEvent(DeltaVParser.getEventTimeArrayValue(i));
			
				deltaVStartEvent.setLabel(DeltaVParser.getEventItemArrayValue(i));
				
				rowTBR.addInterval(deltaVStartEvent);
				
				JaretDate deltaVPart1 = new JaretDate(DeltaVParser.getEventTimeArrayValue(i).copy());
				deltaVPart1 = deltaVPart1.backHours(3);
		
				FancyEvent deltaVPart1Event = new FancyEvent(deltaVPart1);
				System.out.println(deltaVPart1Event.toString());
				deltaVPart1Event.setLabel(DeltaVParser.getEventItemArrayValue(i).concat("- 3 hours"));
			
				rowTBR.addInterval(deltaVPart1Event);
			}
			
			
			if (DeltaVParser.getEventItemArrayValue(i).startsWith("ADV") && DeltaVParser.getEventItemArrayValue(i).contains("Start") ){
				FancyEvent eventInterval = new FancyEvent(DeltaVParser.getEventTimeArrayValue(i));
				eventInterval.setLabel(DeltaVParser.getEventItemArrayValue(i));
				rowTBR.addInterval(eventInterval);																																																																																																																																																																																																										
			}
					
		}
			
		ghantChartModel.addRow(-1,rowTBR);

		return ghantChartModel;
		
	}
	
}
	
