package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.ui.NoResultUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class NotificationSidebar extends Composite {

	private static NotificationSidebarUiBinder uiBinder = GWT.create(NotificationSidebarUiBinder.class);

	interface NotificationSidebarUiBinder extends UiBinder<Widget, NotificationSidebar> {
	}
	
	@UiField HTMLPanel notificationPanel;

	public NotificationSidebar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		new NoResultUtil(HuskyResources.INSTANCE.ic_gray_notification(), "No Recent Notifications", notificationPanel);
	}

}
