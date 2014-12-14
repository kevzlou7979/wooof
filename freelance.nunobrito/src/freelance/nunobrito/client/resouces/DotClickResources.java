package freelance.nunobrito.client.resouces;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface DotClickResources extends ClientBundle {
	public static final DotClickResources INSTANCE = GWT
			.create(DotClickResources.class);

	ImageResource bg_login();
	
	ImageResource ic_logout();
	
	ImageResource ic_profile();
	
	ImageResource ic_facebook();
	
	ImageResource ic_calendar();
	
	ImageResource ic_error();
	
	ImageResource ic_success();
	
	ImageResource ic_edit();
	
	ImageResource ic_bell();
	
	ImageResource ic_info();
	
	ImageResource ic_post();
	
	ImageResource ic_lock();
	
	ImageResource ic_logo();
	
	ImageResource ic_footer_logo();
	
	ImageResource ic_company();
	
	ImageResource ic_share();
	
	ImageResource ic_fb();
	
	ImageResource ic_google();
	
	ImageResource ic_twitter();
	
	ImageResource bg_main();
	
	DotClickCSS dotclickcss();

	DotClickMobileCSS dotclickmobilecss();
}
