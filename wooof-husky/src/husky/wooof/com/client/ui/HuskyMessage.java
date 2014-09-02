package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class HuskyMessage {

	private static HTMLPanel panel = new HTMLPanel("");
	private static Image image = new Image();
	private static Label label = new Label();

	public static void showMessage(boolean isSuccess, HTMLPanel container, String message) {
		label.addStyleName(HuskyResources.INSTANCE.huskycss().huskyMessageLbl());
		image.addStyleName(HuskyResources.INSTANCE.huskycss().huskyMessageImg());
		if (isSuccess) {
			image.setResource(HuskyResources.INSTANCE.ic_success());
			label.addStyleName(HuskyResources.INSTANCE.huskycss().huskySuccess());
		}
		else {
			image.setResource(HuskyResources.INSTANCE.ic_error());
			label.addStyleName(HuskyResources.INSTANCE.huskycss().huskyError());
		}

		label.setText(message);
		panel.add(image);
		panel.add(label);
		container.add(panel);
	}

	public static void hideMessage() {
		panel.removeFromParent();
	}

}
