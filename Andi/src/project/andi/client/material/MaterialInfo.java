package project.andi.client.material;


import project.andi.client.resources.AndiResources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class MaterialInfo {

	
	public MaterialInfo() {
		
	}
	
	public static void showInfo(HTMLPanel panel, ImageResource resource, String message){
		panel.clear();
		
		HTMLPanel container = new HTMLPanel("");
		container.addStyleName(AndiResources.INSTANCE.andicss().andiInfo());
		Label label = new Label(message);
		container.add(new Image(resource));
		container.add(label);
		panel.add(container);
		
	}
	
}
