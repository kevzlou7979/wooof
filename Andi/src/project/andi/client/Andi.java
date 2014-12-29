package project.andi.client;

import project.andi.client.page.MaintenancePage;
import project.andi.client.resources.AndiResources;
import project.andi.client.resources.ResourcesLoader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Andi implements EntryPoint {
	
	public void onModuleLoad() {
		new ResourcesLoader(AndiResources.INSTANCE);
		RootPanel.get().clear();
		RootPanel.get().add(new MaintenancePage());
	}
}
