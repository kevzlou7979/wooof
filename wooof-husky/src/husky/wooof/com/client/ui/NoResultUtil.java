package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class NoResultUtil extends Composite {

	private static NoResultUtilUiBinder uiBinder = GWT
			.create(NoResultUtilUiBinder.class);

	interface NoResultUtilUiBinder extends UiBinder<Widget, NoResultUtil> {
	}

	@UiField Image imgIcon;
	@UiField Label lblMessage;
	
	public NoResultUtil(ImageResource image, String message, HTMLPanel panel) {
		initWidget(uiBinder.createAndBindUi(this));
		imgIcon.setResource(image);
		lblMessage.setText(message);
		panel.add(this);
	}

}
