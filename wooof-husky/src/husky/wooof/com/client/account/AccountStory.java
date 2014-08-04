package husky.wooof.com.client.account;

import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AccountStory extends Composite {

	private static AccountStoryUiBinder uiBinder = GWT
			.create(AccountStoryUiBinder.class);

	interface AccountStoryUiBinder extends UiBinder<Widget, AccountStory> {
	}

	@UiField Label lblTagLine, lblIntroduction;
	
	private HuskyUser user;
	
	public AccountStory() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public HuskyUser getUser() {
		return user;
	}

	public void setUser(HuskyUser user) {
		this.user = user;
		if(user.getTagline()!=null){
			lblTagLine.setText(user.getTagline());
		}
		if(user.getIntroduction()!=null){
			lblIntroduction.setText(user.getIntroduction());
		}
	}

}
