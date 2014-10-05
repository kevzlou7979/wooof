package husky.wooof.com.client.dialog;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.sidebar.CardInfoSidebar;
import husky.wooof.com.client.ui.HuskyCkeditor;
import husky.wooof.com.client.ui.QuizItem;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
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
	@UiField HTMLPanel mainPanel, headerPanel;
	private Composite com;
	private HuskyDialog dialog;
	
	public ActionDialog(String type, String message, String title) {
		initWidget(uiBinder.createAndBindUi(this));
		imgIcon.setResource(HuskyResources.INSTANCE.ic_action_warning());
		switch (type) {
		case IHuskyConstants.ACTION_WARN:
			
			break;
		case IHuskyConstants.ACTION_ERROR:
			
			break;
		case IHuskyConstants.ACTION_INFO:
			headerPanel.getElement().getStyle().setBackgroundColor("#4374e0");
			break;

		default:
			break;
		}
		lblTitle.setText(title);
		lblDesc.setText(message);
		if(message.isEmpty()){
			lblDesc.removeFromParent();
		}
		
	}
	
	@UiHandler("btnOk")
	void onOk(ClickEvent e){
		if(com instanceof CardInfoSidebar){
			((CardInfoSidebar) com).unsubscribeFromCard();
		}else if(com instanceof HuskyCkeditor){
			((HuskyCkeditor) com).setSource();
		}else if(com instanceof QuizItem){
			((QuizItem) com).deleteQuizItem();
		}
		dialog.hide();
	}
	
	@UiHandler("btnCancel")
	void onCancel(ClickEvent e){
		this.dialog.hide();
	}

	public void setDialog(HuskyDialog dialog) {
		this.dialog = dialog;
		dialog.hide();
	}

	public HTMLPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(HTMLPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public Composite getCom() {
		return com;
	}

	public void setCom(Composite com) {
		this.com = com;
	}
	
	

}
