package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateMain extends Composite {

	private static CreateMainUiBinder uiBinder = GWT
			.create(CreateMainUiBinder.class);

	interface CreateMainUiBinder extends UiBinder<Widget, CreateMain> {
	}

	public CreateMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
