package husky.wooof.com.client.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HuskyPopup extends Composite {

	private static HuskyPopupUiBinder uiBinder = GWT
			.create(HuskyPopupUiBinder.class);

	interface HuskyPopupUiBinder extends UiBinder<Widget, HuskyPopup> {
	}

	public HuskyPopup() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
