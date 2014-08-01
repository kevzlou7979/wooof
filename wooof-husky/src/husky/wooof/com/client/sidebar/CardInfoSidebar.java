package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CardInfoSidebar extends Composite {

	private static CardInfoSidebarUiBinder uiBinder = GWT
			.create(CardInfoSidebarUiBinder.class);

	interface CardInfoSidebarUiBinder extends UiBinder<Widget, CardInfoSidebar> {
	}

	public CardInfoSidebar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
