package wooof.huskylabs.client;

import wooof.huskylabs.client.services.UserAccountService;
import wooof.huskylabs.shared.HuskyUser;
import wooof.huskylabs.shared.IHuskyConstants;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


@SuppressWarnings("deprecation")
public class HuskyLogin extends Composite {

	private static HuskyLoginUiBinder uiBinder = GWT
			.create(HuskyLoginUiBinder.class);

	interface HuskyLoginUiBinder extends UiBinder<Widget, HuskyLogin> {
	}

	@UiField TextBox txtEmail;
	@UiField PasswordTextBox txtPassword;
	
	@UiField Image imgUser;
	@UiField Label lblEmail;
	
	public HuskyLogin() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("btnAuthGoogle")
	void onAuthGoogle(ClickEvent e){
		AuthRequest req = new AuthRequest(IHuskyConstants.AUTH_URL, IHuskyConstants.CLIENT_ID).withScopes(IHuskyConstants.PLUS_ME_SCOPE, IHuskyConstants.GOOGLE_MAIL_SCOPE);
		Auth.get().login(req, new Callback<String, Throwable>() {
			  @Override
			  public void onSuccess(final String token) {
				  UserAccountService.Connect.getService().login(token, new AsyncCallback<HuskyUser>() {
					
					@Override
					public void onSuccess(HuskyUser result) {
						if(result!=null){
							imgUser.setUrl(result.getProfilePic());
							lblEmail.setText(result.getEmail());
						}
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
	
	@UiHandler("btnSignIn")
	void onSignIn(ClickEvent e){
		
	}

}
