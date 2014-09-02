package husky.wooof.com.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyDialog extends DialogBox {

	private static HuskyDialogUiBinder uiBinder = GWT.create(HuskyDialogUiBinder.class);

	interface HuskyDialogUiBinder extends UiBinder<Widget, HuskyDialog> {
	}

	@UiField
	HTMLPanel dialogPanel;

	public HuskyDialog(Widget composite) {

		setWidget(uiBinder.createAndBindUi(this));
		dialogPanel.add(composite);
	}

	@UiHandler("btnClose")
	void onCloseDialog(ClickEvent e) {
		hide();
	}

}
