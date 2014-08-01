package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.ui.ChatMessageItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChatSidebar extends Composite {

	private static ChatSidebarUiBinder uiBinder = GWT
			.create(ChatSidebarUiBinder.class);

	interface ChatSidebarUiBinder extends UiBinder<Widget, ChatSidebar> {
	}

	@UiField HTMLPanel chatMessagePanel;
	
	public ChatSidebar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		chatMessagePanel.add(new ChatMessageItem());
		chatMessagePanel.add(new ChatMessageItem());
		chatMessagePanel.add(new ChatMessageItem());
		chatMessagePanel.add(new ChatMessageItem());
	}

}
