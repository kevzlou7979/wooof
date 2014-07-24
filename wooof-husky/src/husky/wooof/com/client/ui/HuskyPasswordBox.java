package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class HuskyPasswordBox extends PasswordTextBox {

	private String placeholder;

	public HuskyPasswordBox() {
		this.addStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBox());
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.getElement().setAttribute("placeholder", placeholder);
	}

}
