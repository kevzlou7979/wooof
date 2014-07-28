package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.TextArea;

public class HuskyTextArea extends TextArea{

	private String placeholder;
	
	public HuskyTextArea() {
		this.addStyleName(HuskyResources.INSTANCE.huskycss().huskyTextArea());
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.getElement().setAttribute("placeholder", placeholder);
	}
	
}
