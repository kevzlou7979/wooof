package labs.wooof.com.client;

import labs.wooof.com.client.main.WooofMain;
import labs.wooof.com.client.resources.WooofResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Wooof implements EntryPoint {
	
	public void onModuleLoad() {
		WooofResources.INSTANCE.wooofcss().ensureInjected();
		RootPanel.get().add(new WooofMain());
	}
}
