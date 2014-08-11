package labs.wooof.com.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface WooofResources extends ClientBundle{

	public static final WooofResources INSTANCE = GWT.create(WooofResources.class);
	
	ImageResource bg_wooof();
	ImageResource logo_husky();
	ImageResource logo_poodle();
	
	@Source("wooofcss.css")
	WooofCSS wooofcss();
}
