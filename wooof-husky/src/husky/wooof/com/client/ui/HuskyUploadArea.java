package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyUploadArea extends Composite {

	private static HuskyUploadAreaUiBinder uiBinder = GWT
			.create(HuskyUploadAreaUiBinder.class);

	interface HuskyUploadAreaUiBinder extends UiBinder<Widget, HuskyUploadArea> {
	}

	@UiField Label lblDescription;
	
	private String message;
	
	public HuskyUploadArea() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		lblDescription.setText(message);
	}
	
}
