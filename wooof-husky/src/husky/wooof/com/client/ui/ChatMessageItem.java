package husky.wooof.com.client.ui;

import husky.wooof.com.shared.HuskyChatMessage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ChatMessageItem extends Composite {

	private static ChatMessageItemUiBinder uiBinder = GWT
			.create(ChatMessageItemUiBinder.class);

	interface ChatMessageItemUiBinder extends UiBinder<Widget, ChatMessageItem> {
	}

	@UiField Label lblName, lblMessage;
	
	public ChatMessageItem(HuskyChatMessage chatMessage) {
		initWidget(uiBinder.createAndBindUi(this));
		lblName.setText(chatMessage.getUser());
		lblMessage.setText(chatMessage.getMessage());
	}

}
