package husky.wooof.com.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface HuskyResources extends ClientBundle{
	public static final HuskyResources INSTANCE = GWT.create(HuskyResources.class);
	
	ImageResource husky_logo();
	ImageResource ic_success();
	ImageResource ic_error();
	ImageResource ic_image();
	ImageResource ic_cards();
	ImageResource ic_trips();
	ImageResource ic_default_card();
	ImageResource ic_card();
	
    HuskyCSS huskyCSS();
   
}
