package husky.wooof.com.client;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.client.ui.HuskyListBox;
import husky.wooof.com.client.ui.HuskyLoading;
import husky.wooof.com.client.ui.HuskyMessage;
import husky.wooof.com.client.ui.HuskyPasswordBox;
import husky.wooof.com.client.ui.HuskyTextBox;
import husky.wooof.com.shared.FieldVerifier;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class HuskyLogin extends Composite {

	private static HuskyLoginUiBinder uiBinder = GWT.create(HuskyLoginUiBinder.class);

	interface HuskyLoginUiBinder extends UiBinder<Widget, HuskyLogin> {
	}

	@UiField
	HTMLPanel messagePanel, signUpPanel, signInPanel, formPanel, loginPanel, maintenancePanel;
	@UiField
	Image imgLogo;
	@UiField
	HuskyTextBox txtEmail;
	@UiField
	HuskyPasswordBox txtPassword, txtRPassword, txtRConPassword;
	@UiField
	HuskyTextBox txtRFirstName, txtRLastName, txtREmail;
	@UiField
	Label signUpNav, lblTitle;
	@UiField
	HuskyListBox lstGender;

	public HuskyLogin() {
		initWidget(uiBinder.createAndBindUi(this));
		loadSignInPanel();
		//signUpNav.removeFromParent();
		onHocusFocus(true);
	}

	public void onHocusFocus(boolean isTrue){
		if(isTrue){
			maintenancePanel.removeFromParent();
			loginPanel.removeStyleName(HuskyResources.INSTANCE.huskycss().loginPanel());
			//formPanel.removeFromParent();
			imgLogo.removeFromParent();
			lblTitle.removeFromParent();
			txtEmail.setText("kevzlou7979@gmail.com");
			txtPassword.setText("123123123");
		}
	}

	private void loadSignUpPanel() {
		HuskyMessage.hideMessage();
		signUpPanel.setVisible(true);
		signInPanel.setVisible(false);
	}

	private void loadSignInPanel() {
		HuskyMessage.hideMessage();
		signInPanel.setVisible(true);
		signUpPanel.setVisible(false);
	}

	private void loadMainPanel(HuskyUser user) {
		RootPanel.get().clear();
		RootPanel.get().add(new HuskyMain(user));
	}

	private void signInAccount() {
		messagePanel.setVisible(true);
		if (FieldVerifier.isValidFields(signInPanel, messagePanel) && FieldVerifier.isValidEmailFields(txtEmail, messagePanel)) {
			HuskyLoading.showLoading(true, formPanel, "Signing In", 0, IHuskyConstants.LOADING_CIRCLE);
			HuskyMessage.hideMessage();
			signInPanel.setVisible(false);
			imgLogo.addStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
			UserAccountService.Connect.getService().login(txtEmail.getText(), txtPassword.getText(), new AsyncCallback<HuskyUser>() {

				@Override
				public void onSuccess(final HuskyUser result) {
					Timer timer = new Timer() {
						@Override
						public void run() {
							imgLogo.removeStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
							HuskyLoading.showLoading(false);
							if (result != null) {
								loadMainPanel(result);
								// RootPanel.get().add(new ChatMain(result));
							}
							else {
								HuskyMessage.showMessage(false, messagePanel, "Incorrect Username or Password.");
								signInPanel.setVisible(true);
								signUpPanel.setVisible(false);
							}
						}
					};

					timer.schedule(1000);

				}

				@Override
				public void onFailure(Throwable caught) {
					imgLogo.removeStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
					HuskyLoading.showLoading(false);
					HuskyMessage.showMessage(false, messagePanel, caught.getMessage());
				}
			});
		}
	}

	private void signUpAccount() {
		messagePanel.setVisible(true);
		if (FieldVerifier.isValidFields(signUpPanel, messagePanel) && FieldVerifier.isValidEmailFields(txtREmail, messagePanel) && FieldVerifier.isPasswordMatched(messagePanel, txtRPassword, txtRConPassword)) {
			HuskyLoading.showLoading(true, formPanel, "Signing Up", 0, IHuskyConstants.LOADING_CIRCLE);
			signUpPanel.setVisible(false);
			imgLogo.addStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());

			UserAccountService.Connect.getService().register(txtRFirstName.getText(), txtRLastName.getText(), txtREmail.getText(), txtRPassword.getText(), lstGender.getItemText(lstGender.getSelectedIndex()), new AsyncCallback<HuskyUser>() {

				@Override
				public void onSuccess(final HuskyUser result) {
					imgLogo.removeStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
					loadMainPanel(result);

				};

				@Override
				public void onFailure(Throwable caught) {
					imgLogo.removeStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
					loadSignUpPanel();
					HuskyMessage.showMessage(false, messagePanel, caught.getMessage());
				}
			});
		}
	}

	@UiHandler("signInNav")
	void onSignInNav(ClickEvent e) {
		loadSignInPanel();
	}

	@UiHandler("signUpNav")
	void onSignUpNav(ClickEvent e) {
		loadSignUpPanel();
	}

	@UiHandler("txtEmail")
	void onEnterEmail(KeyDownEvent e) {
		if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			signInAccount();
		}
	}

	@UiHandler("txtPassword")
	void onEnterPassword(KeyDownEvent e) {
		if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			signInAccount();
		}
	}

	@UiHandler("btnSignIn")
	void onSignInAccount(ClickEvent e) {
		signInAccount();
	}

	@UiHandler("btnSignUp")
	void onSignUpAccount(ClickEvent e) {
		signUpAccount();
	}

	@UiHandler("txtREmail")
	void onChangeText(KeyUpEvent e) {
		imgLogo.addStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
	}

	@UiHandler("txtRConPassword")
	void onEnterConPassword(KeyDownEvent e) {
		if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			signUpAccount();
		}
	}
	
	@UiHandler("lblSecret")
	void onSecret(ClickEvent e){
		loginPanel.add(formPanel);
	}
	
	private void onOauth(AuthRequest req, final String oauthUrl){
		
		Auth.get().login(req, new Callback<String, Throwable>() {
			  @Override
			  public void onSuccess(final String token) {
				  UserAccountService.Connect.getService().login(oauthUrl + token, new AsyncCallback<HuskyUser>() {
					
					@Override
					public void onSuccess(HuskyUser result) {
						imgLogo.removeStyleName(HuskyResources.INSTANCE.huskycss().rotateLogo());
						loadMainPanel(result);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			  }
			  @Override
			  public void onFailure(Throwable caught) {
				  Window.alert(caught.getMessage());
			  }
			});
	}
	
	@UiHandler("btnGooglePlusAuth")
	void onGooglePlusAuth(ClickEvent e){
		AuthRequest req = new AuthRequest(IHuskyConstants.GOOGLE_AUTH_URL, IHuskyConstants.GOOGLE_CLIENT_ID).withScopes(IHuskyConstants.GOOGLE_PLUS_SCOPE, IHuskyConstants.GOOGLE_MAIL_SCOPE);
		onOauth(req, IHuskyConstants.GOOGLE_TOKEN);
	}
	
	@UiHandler("btnFacebookAuth")
	void onFacebookAuth(ClickEvent e){
		AuthRequest req = new AuthRequest(IHuskyConstants.FACEBOOK_AUTH_URL, IHuskyConstants.FACEBOOK_CLIENT_ID).withScopes(IHuskyConstants.FACEBOOK_EMAIL_SCOPE, IHuskyConstants.FACEBOOK_BIRTHDAY_SCOPE).withScopeDelimiter(",");
		onOauth(req, IHuskyConstants.FACEBOOK_TOKEN);
	}

}
