package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NotificationSidebar extends Composite {

	private static NotificationSidebarUiBinder uiBinder = GWT.create(NotificationSidebarUiBinder.class);

	interface NotificationSidebarUiBinder extends UiBinder<Widget, NotificationSidebar> {
	}

	public NotificationSidebar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
