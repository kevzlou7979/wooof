package husky.wooof.com.client.dialog;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.sidebar.CardInfoSidebar;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ActionDialog extends Composite {

	private static ActionDialogUiBinder uiBinder = GWT
			.create(ActionDialogUiBinder.class);

	interface ActionDialogUiBinder extends UiBinder<Widget, ActionDialog> {
	}

	@UiField Image imgIcon;
	@UiField Label lblTitle, lblDesc;
	private Composite com;
	private HuskyDialog dialog;
	
	public ActionDialog(String type, String message, Composite com) {
		initWidget(uiBinder.createAndBindUi(this));
		imgIcon.setResource(HuskyResources.INSTANCE.ic_action_warning());
		switch (type) {
		case IHuskyConstants.ACTION_WARN:
			lblTitle.setText("Oops Wait a second...");
			break;
		case IHuskyConstants.ACTION_ERROR:
			lblTitle.setText("Sorry for the inconvience...");
			break;
		case IHuskyConstants.ACTION_INFO:
			lblTitle.setText("Just for the info...");
			break;

		default:
			break;
		}
		lblDesc.setText(message);
		this.com = com;
	}
	
	@UiHandler("btnOk")
	void onOk(ClickEvent e){
		if(com instanceof CardInfoSidebar){
			((CardInfoSidebar) com).unsubscribeFromCard();
		}
	}
	
	@UiHandler("btnCancel")
	void onCancel(ClickEvent e){
		dialog.hide();
	}

	public void setDialog(HuskyDialog dialog) {
		this.dialog = dialog;
	}

}
