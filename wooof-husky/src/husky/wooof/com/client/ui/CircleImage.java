package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CircleImage extends Composite {

	private static CircleImageUiBinder uiBinder = GWT
			.create(CircleImageUiBinder.class);

	interface CircleImageUiBinder extends UiBinder<Widget, CircleImage> {
	}

	public CircleImage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
