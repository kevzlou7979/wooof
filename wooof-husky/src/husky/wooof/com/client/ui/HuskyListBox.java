package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.ListBox;

public class HuskyListBox extends ListBox{

	public HuskyListBox() {
		this.addStyleName(HuskyResources.INSTANCE.huskycss().huskyTextBox());
	}
	
}
