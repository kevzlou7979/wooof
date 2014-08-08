package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class CircleImage extends Composite {

	private static CircleImageUiBinder uiBinder = GWT
			.create(CircleImageUiBinder.class);

	interface CircleImageUiBinder extends UiBinder<Widget, CircleImage> {
	}

	@UiField Image imgProfile;
	private ImageResource resource;
	
	public CircleImage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setImageProfile(String url){
		imgProfile.setUrl(url);
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		imgProfile.setResource(resource);
	}

}
