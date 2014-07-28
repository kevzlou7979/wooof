package husky.wooof.com.client.ui;

import husky.wooof.com.client.services.CardService;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyUploadArea extends Composite {

	private static HuskyUploadAreaUiBinder uiBinder = GWT
			.create(HuskyUploadAreaUiBinder.class);

	interface HuskyUploadAreaUiBinder extends UiBinder<Widget, HuskyUploadArea> {
	}

	@UiField
	Label lblDescription;
	@UiField
	Button uploadButton;

	@UiField
	FormPanel uploadForm;

	@UiField
	FileUpload uploadField;

	private String message;

	public HuskyUploadArea() {
		initWidget(uiBinder.createAndBindUi(this));
		// Disable the button until we get the URL to POST to
		uploadButton.setText("Loading...");
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		uploadButton.setEnabled(false);
		uploadField.setName("image");

		// Now we use out GWT-RPC service and get an URL
		startNewBlobstoreSession();

		// Once we've hit submit and it's complete, let's set the form to a new
		// session.
		// We could also have probably done this on the onClick handler
		uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

					@Override
					public void onSubmitComplete(SubmitCompleteEvent event) {
						uploadForm.reset();
						startNewBlobstoreSession();
						 // This is what gets the result back - the content-type *must* be
					    // text-html
					    System.out.println(event.getResults());
					}
				});
		
	}

	private void startNewBlobstoreSession() {
		CardService.Connect.getService().getBlobstoreUploadUrl(
				new AsyncCallback<String>() {

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

}
