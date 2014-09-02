package husky.wooof.com.client.account;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.services.BlobService;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.UploadedImage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Widget;

public class AccountProfilePic extends Composite {

	private static AccountProfilePicUiBinder uiBinder = GWT.create(AccountProfilePicUiBinder.class);

	interface AccountProfilePicUiBinder extends UiBinder<Widget, AccountProfilePic> {
	}

	@UiField
	Button uploadButton;

	@UiField
	FormPanel uploadForm;

	@UiField
	FileUpload uploadField;

	private HuskyMain huskyMain;

	public AccountProfilePic() {
		initWidget(uiBinder.createAndBindUi(this));
		uploadButton.setText("Loading...");
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		uploadButton.setEnabled(false);
		uploadField.setName("image");
		startNewBlobstoreSession();

		uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {

				uploadForm.reset();
				startNewBlobstoreSession();
				String key = "";
				if (event.getResults() != null) {
					key = event.getResults();
				}
				else {
					key = "agt3b29vZi1odXNreXIaCxINVXBsb2FkZWRJbWFnZRiAgICAgICCCQw";
				}
				getImageUrl(key);
			}

			private void getImageUrl(String key) {
				BlobService.Connect.getService().getUploadImage(key, new AsyncCallback<UploadedImage>() {

					@Override
					public void onSuccess(UploadedImage result) {

						HuskyUser user = huskyMain.getUser();
						user.setProfilePic(result.getServingUrl());
						UserAccountService.Connect.getService().updateUser(user, new AsyncCallback<HuskyUser>() {

							@Override
							public void onSuccess(HuskyUser result) {
								huskyMain.getHuskyNavigation().getImgProfile().setImageProfile(result.getProfilePic());
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
		});
	}

	private void startNewBlobstoreSession() {
		BlobService.Connect.getService().getBlobstoreUploadUrl(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				uploadForm.setAction(result);
				uploadButton.setText("Upload");
				uploadButton.setEnabled(true);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e) {
		uploadForm.submit();
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}

}
