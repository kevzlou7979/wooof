package husky.wooof.com.client.navigation;

import husky.wooof.com.client.HuskyLogin;
import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.resources.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyNavigation extends Composite {

	private static HuskyNavigationUiBinder uiBinder = GWT
			.create(HuskyNavigationUiBinder.class);

	interface HuskyNavigationUiBinder extends UiBinder<Widget, HuskyNavigation> {
	}
	
	@UiField HTMLPanel navMenu;
	@UiField Label navCards, navCreate, navTeam,navSettings, navAccount, navLogout;
	
	private HuskyMain huskyMain;
	
	public HuskyNavigation(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		changeNav(navCards, IHuskyConstants.NAV_CARDS);
	}
	
	@UiHandler("navCards")
	void onNavCards(ClickEvent e){
		changeNav(navCards, IHuskyConstants.NAV_CARDS);
	}
	
	@UiHandler("navCreate")
	void onNavCreate(ClickEvent e){
		changeNav(navCreate, IHuskyConstants.NAV_CREATE);
	}
	
	@UiHandler("navSettings")
	void onNavSettings(ClickEvent e){
		changeNav(navSettings, IHuskyConstants.NAV_SETTINGS);
	}
	
	@UiHandler("navTeam")
	void onNavTeam(ClickEvent e){
		changeNav(navTeam, IHuskyConstants.NAV_TEAM);
	}
	
	@UiHandler("navAccount")
	void onNavAccount(ClickEvent e){
		changeNav(navAccount, IHuskyConstants.NAV_ACCOUNT);
	}
	
	@UiHandler("navLogout")
	void onNavLogout(ClickEvent e){
		changeNav(navLogout, IHuskyConstants.NAV_LOGOUT);
	}
	
	private void changeNav(Label menu, int navType){
		for(Widget w : navMenu){
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().lblSimpleActive());
		}
		menu.addStyleName(HuskyResources.INSTANCE.huskycss().lblSimpleActive());
		
		huskyMain.getHuskyMainPanel().clear();
		switch (navType) {
		case IHuskyConstants.NAV_CARDS:
			huskyMain.getHuskyMainPanel().add(huskyMain.getCardsMain());
			break;
		case IHuskyConstants.NAV_CREATE:
			huskyMain.getHuskyMainPanel().add(huskyMain.getCreateMain());
			break;
		case IHuskyConstants.NAV_SETTINGS:
			
			break;
		case IHuskyConstants.NAV_ACCOUNT:
			
			break;
		case IHuskyConstants.NAV_LOGOUT:
			RootPanel.get().clear();
			RootPanel.get().add(new HuskyLogin());
			break;
		default:
			break;
		}
		
	}

}
