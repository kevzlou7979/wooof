package husky.wooof.com.client.ui;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserItem extends Composite {

	private static UserItemUiBinder uiBinder = GWT
			.create(UserItemUiBinder.class);

	interface UserItemUiBinder extends UiBinder<Widget, UserItem> {
	}

	@UiField Label lblFullName, lblEmail;
	@UiField CircleImage imgProfile;
	@UiField HuskyListBox lstUserType;
	
	private HuskyCardNavigation huskyCardNavigation;
	private HuskyUser user;
	
	public UserItem(HuskyUser user, HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		this.user = user;
		imgProfile.setImageProfile(user.getProfilePic());
		lblFullName.setText(user.getFirstName() + " " + user.getLastName());
		lblEmail.setText(user.getEmail());
	}
	
	@UiHandler("btnAddUser")
	void onAddUser(ClickEvent e){
		CardService.Connect.getService().addUserToCard(user, huskyCardNavigation.getWorkspaceMain().getCard(), lstUserType.getValue(lstUserType.getSelectedIndex()), new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				if(lstUserType.getValue(lstUserType.getSelectedIndex()).equals(IHuskyConstants.CARD_ADMIN)){
					huskyCardNavigation.getAddUserSideBar().getHuskyUserListNavigation().setAllAdminUsers();
				}else{
					huskyCardNavigation.getAddUserSideBar().getHuskyUserListNavigation().setAllViewerUsers();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

}
