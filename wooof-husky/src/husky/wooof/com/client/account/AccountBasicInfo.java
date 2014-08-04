package husky.wooof.com.client.account;

import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.client.ui.HuskyEditableLabel;
import husky.wooof.com.client.ui.HuskyMessage;
import husky.wooof.com.shared.FieldVerifier;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class AccountBasicInfo extends Composite{

	private static AccountBasicInfoUiBinder uiBinder = GWT
			.create(AccountBasicInfoUiBinder.class);

	interface AccountBasicInfoUiBinder extends
			UiBinder<Widget, AccountBasicInfo> {
	}

	@UiField HuskyEditableLabel lblFirstName, lblLastName, lblEmail, lblGender;
	@UiField Image btnEdit;
	@UiField FocusPanel panel;
	
	@UiField HTMLPanel editablePanel, actionPanel ,messagePanel;
	private HuskyUser user;
	
	public AccountBasicInfo() {
		initWidget(uiBinder.createAndBindUi(this));
		btnEdit.setVisible(false);
		actionPanel.setVisible(false);
	}
	public HuskyUser getUser() {
		return user;
	}
	public void setUser(HuskyUser user) {
		this.user = user;
		lblFirstName.setText(user.getFirstName());
		lblLastName.setText(user.getLastName());
		lblEmail.setText(user.getEmail());
		lblGender.setText(user.getGender());
	}
	
	@UiHandler("btnEdit")
	void onEditBasicInfo(ClickEvent e){
		for(Widget w : editablePanel){
			if(w instanceof HuskyEditableLabel){
				((HuskyEditableLabel) w).transformToTextBox();
			}
		}
		actionPanel.setVisible(true);
	}
	
	@UiHandler("panel")
	void onHoverHuskyTitle(MouseOverEvent e){
		btnEdit.setVisible(true);
	}
	
	@UiHandler("panel")
	void onHoverOutHuskyTitle(MouseOutEvent e){
		btnEdit.setVisible(false);
	}
	
	@UiHandler("btnSave")
	void onUpdateUser(ClickEvent e){
		if(FieldVerifier.isValidFields(editablePanel, messagePanel) && FieldVerifier.isValidEmailFields(lblEmail.getTxtBox(), messagePanel)){
			user.setEmail(lblEmail.getTxtBox().getText());
			user.setFirstName(lblFirstName.getTxtBox().getText());
			user.setLastName(lblLastName.getTxtBox().getText());
			user.setGender(lblGender.getTxtBox().getText());
			UserAccountService.Connect.getService().updateUser(user, new AsyncCallback<HuskyUser>() {
				
				@Override
				public void onSuccess(HuskyUser result) {
					transformToLabel();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					HuskyMessage.showMessage(false, messagePanel,caught.getMessage());
				}
			});
		}
		
	}
	@UiHandler("btnCancel")
	void onCancel(ClickEvent e){
		transformToLabel();
	}
	
	private void transformToLabel(){
		actionPanel.setVisible(false);
		for(Widget w : editablePanel){
			if(w instanceof HuskyEditableLabel){
				((HuskyEditableLabel) w).transformToLabel();
			}
		}
	}
	
}
