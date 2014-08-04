package husky.wooof.com.client.account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountChangePassword extends Composite {

	private static AccountChangePasswordUiBinder uiBinder = GWT
			.create(AccountChangePasswordUiBinder.class);

	interface AccountChangePasswordUiBinder extends
			UiBinder<Widget, AccountChangePassword> {
	}

	public AccountChangePassword() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
