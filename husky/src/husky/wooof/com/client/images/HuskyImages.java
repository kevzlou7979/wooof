package husky.wooof.com.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface HuskyImages extends ClientBundle{
	public static final HuskyImages INSTANCE = GWT.create(HuskyImages.class);
	
	ImageResource husky_logo();
	
}
