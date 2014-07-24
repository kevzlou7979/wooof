package husky.wooof.com.client;

import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyLogin extends Composite {

	private static HuskyLoginUiBinder uiBinder = GWT
			.create(HuskyLoginUiBinder.class);

	interface HuskyLoginUiBinder extends UiBinder<Widget, HuskyLogin> {
	}

	@UiField HTMLPanel signUpPanel, signInPanel;
	
	public HuskyLogin() {
		initWidget(uiBinder.createAndBindUi(this));
		loadSignInPanel();
	}
	
	private void loadSignUpPanel(){
		signUpPanel.setVisible(true);
		signInPanel.setVisible(false);
	}
	
	private void loadSignInPanel(){
		signInPanel.setVisible(true);
		signUpPanel.setVisible(false);
	}

	@UiHandler("imgLogo")
	void onLoadMain(ClickEvent e){
		RootPanel.get().clear();
		RootPanel.get().add(new HuskyMain());
		HuskyUser user = new HuskyUser();
		user.setEmail("kevzlou7979@gmail.com");
		UserAccountService.Connect.getService().getUser(user , new AsyncCallback<HuskyUser>() {
			
			@Override
			public void onSuccess(HuskyUser result) {
				Window.alert("Successfully REgistered " + result.getEmail());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	@UiHandler("signInNav")
	void onSignInNav(ClickEvent e){
		loadSignInPanel();
	}
	
	@UiHandler("signUpNav")
	void onSignUpNav(ClickEvent e){
		loadSignUpPanel();
	}
	
}
