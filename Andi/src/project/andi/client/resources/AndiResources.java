package project.andi.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface AndiResources extends ClientBundle {
	public static final AndiResources INSTANCE = GWT.create(AndiResources.class);

	ImageResource andi();
	ImageResource bg_andi();
	ImageResource avatar_man();
	ImageResource avatar_woman();
	ImageResource ic_map();
	ImageResource ic_time();
	ImageResource logo_info();
	AndiCSS andicss();
	AndiMobileCSS andimobilecss();
	
}
