package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.account.AccountBasicInfo;
import husky.wooof.com.client.account.AccountChangePassword;
import husky.wooof.com.client.account.AccountContactInfo;
import husky.wooof.com.client.account.AccountEducation;
import husky.wooof.com.client.account.AccountProfilePic;
import husky.wooof.com.client.account.AccountStory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountMain extends Composite{

	private static AccountMainUiBinder uiBinder = GWT
			.create(AccountMainUiBinder.class);

	interface AccountMainUiBinder extends UiBinder<Widget, AccountMain> {
	}

	@UiField AccountBasicInfo accountBasicInfo;
	@UiField AccountStory accountStory;
    @UiField AccountProfilePic accountProfilePic;
    @UiField AccountEducation accountEducation;
    @UiField AccountContactInfo accountContactInfo;
    @UiField AccountChangePassword accountChangePassword;
	
	public AccountMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		accountBasicInfo.setUser(huskyMain.getUser());
		accountStory.setUser(huskyMain.getUser());
		accountProfilePic.setHuskyMain(huskyMain);
	}

	
}
