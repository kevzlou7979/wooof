package husky.wooof.com.client;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Husky implements EntryPoint {
	
	public void onModuleLoad() {
		HuskyResources.INSTANCE.huskyCSS().ensureInjected();
		RootPanel.get().add(new HuskyLogin());
	}
}
