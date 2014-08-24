package husky.wooof.com.client.ui;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.shared.IHuskyChatMessage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
	
	public ChatMessageItem(IHuskyChatMessage chatMessage, HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		if(!chatMessage.getUser().equals(huskyCardNavigation.getWorkspaceMain().getHuskyMain().getUser().getEmail())){
			imgProfile.getElement().getStyle().setBorderColor("#C4D0E5");
		}
		lblTime.setText(chatMessage.getCreationDate().toString());
		lblName.setText(chatMessage.getUser());
		lblMessage.setText(chatMessage.getMessage());
		imgProfile.setUrl(chatMessage.getProfilePic());
	}

}
