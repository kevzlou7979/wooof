package labs.wooof.com.client.main;

import labs.wooof.com.client.resources.WooofResources;
import labs.wooof.com.shared.IWooofConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WooofMain extends Composite {

	private static WooofMainUiBinder uiBinder = GWT
			.create(WooofMainUiBinder.class);

	interface WooofMainUiBinder extends UiBinder<Widget, WooofMain> {
	}
	
	@UiField Image huskyProject, poodleProject;
	@UiField Label homeMenu, pressMenu, aboutMenu, contactMenu;
	@UiField HTMLPanel titlePanel, projectItemsPanel, homeTitlePanel, huskyTitlePanel, poodleTitlePanel;
	
	public WooofMain() {
		initWidget(uiBinder.createAndBindUi(this));
		changeNav(homeMenu, IWooofConstants.NAV_HOME);
	}

	@UiHandler("homeMenu")
	void onHomeMenu(ClickEvent e){
		changeNav(homeMenu, IWooofConstants.NAV_HOME);
	}
	
	@UiHandler("huskyProject")
	void onHuskyProject(ClickEvent e){
		changeNav(huskyProject, IWooofConstants.NAV_HUSKY);
	}
	
	@UiHandler("poodleProject")
	void onPoodleProject(ClickEvent e){
		changeNav(poodleProject, IWooofConstants.NAV_POODLE);
	}
	
	@UiHandler("btnCheckHusky")
	void onCheckHusky(ClickEvent e){
		Window.open("husky.wooof.com", "Husky", "");
	}
	
	private void changeNav(Widget w, int type){
		titlePanel.clear();
		for(Widget img : projectItemsPanel){
			img.removeStyleName(WooofResources.INSTANCE.wooofcss().activeOpacity());
		}
		if(w instanceof Image){
			w.addStyleName(WooofResources.INSTANCE.wooofcss().activeOpacity());
		}
		switch (type) {
		
		case IWooofConstants.NAV_HOME:
			titlePanel.add(homeTitlePanel);
			break;
		case IWooofConstants.NAV_HUSKY:
			titlePanel.add(huskyTitlePanel);
			break;
		case IWooofConstants.NAV_POODLE:
			titlePanel.add(poodleTitlePanel);
			break;

		default:
			break;
		}
	}
	
}
