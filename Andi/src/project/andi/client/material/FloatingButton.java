package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FloatingButton extends Composite {

	private static FloatingButtonUiBinder uiBinder = GWT
			.create(FloatingButtonUiBinder.class);

	interface FloatingButtonUiBinder extends UiBinder<Widget, FloatingButton> {
	}

	public FloatingButton() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
