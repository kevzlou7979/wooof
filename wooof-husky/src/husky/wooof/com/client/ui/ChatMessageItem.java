package husky.wooof.com.client.ui;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyChatMessage;
import husky.wooof.com.shared.helper.TimeChecker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ChatMessageItem extends Composite {

	private static ChatMessageItemUiBinder uiBinder = GWT
			.create(ChatMessageItemUiBinder.class);

	interface ChatMessageItemUiBinder extends UiBinder<Widget, ChatMessageItem> {
	}

	@UiField Label lblName, lblMessage, lblTime;
	@UiField Image imgProfile;
	protected HuskyCardNavigation huskyCardNavigation;
	
	public ChatMessageItem(IHuskyChatMessage chatMessage, HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		lblTime.setText(new TimeChecker(chatMessage.getCreationDate()).getTimePassed());
		lblName.setText(chatMessage.getUser());
		lblMessage.setText(chatMessage.getMessage());
		getUser(chatMessage.getUserId());
	}
	
	private void getUser(Long userId){
		UserAccountService.Connect.getService().getUserById(userId, new AsyncCallback<HuskyUser>() {
			
			@Override
			public void onSuccess(HuskyUser result) {
				if(!result.equals(huskyCardNavigation.getWorkspaceMain().getHuskyMain().getUser().getEmail())){
					imgProfile.getElement().getStyle().setBorderColor("#C4D0E5");
					if(!result.getProfilePic().isEmpty()){
						imgProfile.setUrl(result.getProfilePic());
					}else{
						imgProfile.setResource(HuskyResources.INSTANCE.ic_avatar());
					}
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

}
