package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.navigation.HuskyUserListNavigation;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.client.ui.UserItem;
import husky.wooof.com.shared.HuskyUser;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddUserSideBar extends Composite {

	private static AddUserSideBarUiBinder uiBinder = GWT
			.create(AddUserSideBarUiBinder.class);

	interface AddUserSideBarUiBinder extends UiBinder<Widget, AddUserSideBar> {
	}

	@UiField TextBox txtSearch;
	@UiField HTMLPanel searchResultPanel;
	@UiField HuskyUserListNavigation huskyUserListNavigation;
	
	private HuskyCardNavigation huskyCardNavigation;
	
	public AddUserSideBar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		huskyUserListNavigation.setCard(huskyCardNavigation.getWorkspaceMain().getCard());
		huskyUserListNavigation.setHuskyCardNavigation(huskyCardNavigation);
	}

	@UiHandler("txtSearch")
	void onSearchUsers(KeyUpEvent e){
		if(e.getNativeKeyCode() != KeyCodes.KEY_CTRL){
			searchResultPanel.clear();
			UserAccountService.Connect.getService().searchUsers(txtSearch.getText(),huskyCardNavigation.getWorkspaceMain().getCard(), new AsyncCallback<List<HuskyUser>>() {
				
				@Override
				public void onSuccess(List<HuskyUser> result) {
					for(HuskyUser user : result){
						if(!validateExistingUser(user.getId())){
							searchResultPanel.add(new UserItem(user, huskyCardNavigation));
						}
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}
				
				private boolean validateExistingUser(Long id){
					for(HuskyUser user : huskyUserListNavigation.getCardUsers()){
						if(user.getId().equals(id)){
							return true;
						}
					}
					return false;
				}
			});
		}
	}

	public HuskyUserListNavigation getHuskyUserListNavigation() {
		return huskyUserListNavigation;
	}

	public void setHuskyUserListNavigation(
			HuskyUserListNavigation huskyUserListNavigation) {
		this.huskyUserListNavigation = huskyUserListNavigation;
	}
	
	
	
}
