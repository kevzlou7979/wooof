package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ChatMessageItem extends Composite {

	private static ChatMessageItemUiBinder uiBinder = GWT
			.create(ChatMessageItemUiBinder.class);

	interface ChatMessageItemUiBinder extends UiBinder<Widget, ChatMessageItem> {
	}

	public ChatMessageItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
