package de.vogella.rc.intro.first;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;
import org.eclipse.ui.IViewPart;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		layout.setFixed(false);
		layout.setEditorAreaVisible(true);
	
		String editorArea = layout.getEditorArea();
		String newEditor = layout.ID_EDITOR_AREA;
		
		
		/**
		 * Add the clock view at the top. The idea here is that it is not movable. A 0.12f ration appears to 
		 * make it 'fit' correctly. Note that in this version of RCP there is no way to make the view
		 * non re-sizable. 
		 */
		
		
		layout.addStandaloneView("robinson.schedule.digital.clock", false, IPageLayout.TOP, 0.12f, editorArea);
		
		//layout.addView(editorArea, IPageLayout.RIGHT, 0.5f, GhanttChartView.ID);
		
		
		
		//layout.addView("de.robinson.ghanttChartView", IPageLayout.RIGHT, 0.5f, newEditor);
		
		layout.addStandaloneView("de.robinson.ghanttChartView", true, IPageLayout.TOP, 0.5f, newEditor);
		
		layout.addStandaloneView("robinson.activitylist", false, IPageLayout.RIGHT, .90f, editorArea);
		
		//layout.addStandaloneView("de.robinson.ghanttChartView", false, IPageLayout.TOP, 0.20f, layout.getEditorArea());
		
		

		
	}
}
