package husky.wooof.com.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface HuskyResources extends ClientBundle {
	public static final HuskyResources INSTANCE = GWT.create(HuskyResources.class);

	ImageResource husky_logo();

	ImageResource logo_husky_maintenance();

	ImageResource ic_success();

	ImageResource ic_error();

	ImageResource ic_image();

	ImageResource ic_cards();

	ImageResource ic_trips();

	ImageResource ic_default_card();

	ImageResource ic_card();

	ImageResource ic_nav_add_user_white();

	ImageResource ic_nav_chat_white();

	ImageResource ic_nav_info_white();

	ImageResource ic_nav_notification();

	ImageResource ic_nav_leave();

	ImageResource ic_avatar();

	ImageResource ic_account_basic_info();

	ImageResource ic_account_change_password();

	ImageResource ic_account_contact_info();

	ImageResource ic_account_education();

	ImageResource ic_account_profile_pic();

	ImageResource ic_account_story();

	ImageResource ic_edit();

	ImageResource ic_lesson();

	ImageResource ic_lesson_type_image();

	ImageResource ic_lesson_type_youtube();

	ImageResource ic_lesson_type_audio();
	
	ImageResource ic_lesson_type_link();
	
	ImageResource ic_lesson_type_place();
	
	ImageResource ic_lesson_type();
	
	ImageResource ic_lesson_material();
	
	ImageResource ic_preview_youtube();
	
	ImageResource ic_preview_map();
	
	ImageResource ic_preview_audio();

	ImageResource ic_preview_link();
	
	ImageResource bg_husky();
	
	ImageResource ic_mobile_nav();
	
	HuskyCSS huskycss();
	
	HuskyMobileCSS huskymobilecss();
	

}
