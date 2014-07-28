package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.Button;

public class HuskyButton extends Button{

	public HuskyButton() {
		this.removeStyleName("gwt-Button");
		this.addStyleName(HuskyResources.INSTANCE.huskycss().huskyButton());
	}
	
}
