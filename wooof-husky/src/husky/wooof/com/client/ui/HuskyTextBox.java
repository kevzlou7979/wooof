package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.TextBox;

public class HuskyTextBox extends TextBox {

	private String placeholder;

	public HuskyTextBox() {
		this.addStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBox());
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.getElement().setAttribute("placeholder", placeholder);
	}

}
