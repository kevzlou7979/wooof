package husky.wooof.com.client;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.resources.ResourcesLoader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Husky  implements EntryPoint {

	public void onModuleLoad() {
		new ResourcesLoader(HuskyResources.INSTANCE);
		RootPanel.get().add(new HuskyLogin());
	}
}
