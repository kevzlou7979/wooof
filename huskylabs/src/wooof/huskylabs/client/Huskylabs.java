package wooof.huskylabs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Huskylabs implements EntryPoint {
	
	public void onModuleLoad() {
		RootPanel.get().add(new HuskyLogin());
	}
}
