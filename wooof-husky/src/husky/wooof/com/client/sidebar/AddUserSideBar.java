package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddUserSideBar extends Composite {

	private static AddUserSideBarUiBinder uiBinder = GWT
			.create(AddUserSideBarUiBinder.class);

	interface AddUserSideBarUiBinder extends UiBinder<Widget, AddUserSideBar> {
	}

	public AddUserSideBar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
