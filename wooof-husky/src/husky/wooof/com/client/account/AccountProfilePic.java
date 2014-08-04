package husky.wooof.com.client.account;

import husky.wooof.com.client.ui.HuskyUploadArea;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountProfilePic extends Composite {

	private static AccountProfilePicUiBinder uiBinder = GWT
			.create(AccountProfilePicUiBinder.class);

	interface AccountProfilePicUiBinder extends
			UiBinder<Widget, AccountProfilePic> {
	}
	
	@UiField HuskyUploadArea profilePhoto;

	public AccountProfilePic() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
