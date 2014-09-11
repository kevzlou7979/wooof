package husky.wooof.com.client.ui;

import husky.wooof.com.client.services.BlobService;
import husky.wooof.com.shared.IHuskyConstants;
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
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyUploadArea extends Composite {

	private static HuskyUploadAreaUiBinder uiBinder = GWT.create(HuskyUploadAreaUiBinder.class);

	interface HuskyUploadAreaUiBinder extends UiBinder<Widget, HuskyUploadArea> {
	}

	@UiField
	HTMLPanel uploadPanel;

	@UiField
	Label lblDescription;

	@UiField
	Button uploadButton;

	@UiField
	FormPanel uploadForm;

	@UiField
	FileUpload uploadField;

	@UiField
	Image imgLogo;

	private String message;

	private Image cardImage = new Image();

	public HuskyUploadArea() {
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
					key = "agt3b29vZi1odXNreXIaCxINVXBsb2FkZWRJbWFnZRiAgICAgIDaCQw";
				}
				getImageUrl(key);
			}

			private void getImageUrl(String key) {
				HuskyLoading.showLoading(true, uploadPanel, "", -15, IHuskyConstants.LOADING_CIRCLE);
				BlobService.Connect.getService().getUploadImage(key, new AsyncCallback<UploadedImage>() {

					@Override
					public void onSuccess(UploadedImage result) {
						uploadPanel.clear();
						cardImage.setWidth("100%");
						cardImage.setHeight("100%");
						cardImage.setUrl(result.getServingUrl());
						uploadPanel.add(cardImage);
						HuskyLoading.showLoading(false);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						HuskyLoading.showLoading(false);
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		lblDescription.setText(message);
	}

	public Image getCardImage() {
		return cardImage;
	}

	public void setCardImage(Image cardImage) {
		this.cardImage = cardImage;
	}

}
