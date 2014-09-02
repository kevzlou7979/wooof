package husky.wooof.com.client.account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountEducation extends Composite {

	private static AccountEducationUiBinder uiBinder = GWT.create(AccountEducationUiBinder.class);

	interface AccountEducationUiBinder extends UiBinder<Widget, AccountEducation> {
	}

	public AccountEducation() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
