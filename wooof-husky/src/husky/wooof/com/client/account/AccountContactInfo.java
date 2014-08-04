package husky.wooof.com.client.account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountContactInfo extends Composite {

	private static AccountContactInfoUiBinder uiBinder = GWT
			.create(AccountContactInfoUiBinder.class);

	interface AccountContactInfoUiBinder extends
			UiBinder<Widget, AccountContactInfo> {
	}

	public AccountContactInfo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
