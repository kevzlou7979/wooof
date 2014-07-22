package husky.wooof.com.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HuskyLogin extends Composite {

	private static HuskyLoginUiBinder uiBinder = GWT
			.create(HuskyLoginUiBinder.class);

	interface HuskyLoginUiBinder extends UiBinder<Widget, HuskyLogin> {
	}

	public HuskyLogin() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
