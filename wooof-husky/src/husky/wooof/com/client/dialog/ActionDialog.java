package husky.wooof.com.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ActionDialog extends Composite {

	private static ActionDialogUiBinder uiBinder = GWT
			.create(ActionDialogUiBinder.class);

	interface ActionDialogUiBinder extends UiBinder<Widget, ActionDialog> {
	}

	public ActionDialog(String type) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

}
