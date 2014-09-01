package husky.wooof.com.client.navigation;

import java.util.ArrayList;
import java.util.List;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.UserItem;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyUserListNavigation extends Composite {

	private static HuskyUserListNavigationUiBinder uiBinder = GWT
			.create(HuskyUserListNavigationUiBinder.class);

	interface HuskyUserListNavigationUiBinder extends
			UiBinder<Widget, HuskyUserListNavigation> {
	}

	@UiField Label adminMenu, viewerMenu;
	@UiField HTMLPanel menuPanel,adminPanel,viewerPanel;
	
	private HuskyCard card;
	private HuskyCardNavigation huskyCardNavigation;
	private List<HuskyUser> cardUsers = new ArrayList<HuskyUser>();
	
	public HuskyUserListNavigation() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("adminMenu")
	void onInstructorMenu(ClickEvent e){
		changeNav(adminMenu, IHuskyConstants.NAV_ADMIN);
	}
	
	@UiHandler("viewerMenu")
	void onStudentsMenu(ClickEvent e){
		changeNav(viewerMenu, IHuskyConstants.NAV_VIEWER);
	}
	
	public void changeNav(Label label, int type){
		for(Widget w : menuPanel){
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		}
		switch (type) {
		case IHuskyConstants.NAV_ADMIN:
			setAllAdminUsers();
			break;
		case IHuskyConstants.NAV_VIEWER:
			setAllViewerUsers();
			break;
		default:
			break;
		}
	}
	
	public void setAllAdminUsers(){
		adminMenu.addStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		viewerPanel.setVisible(false);
		adminPanel.setVisible(true);
		adminPanel.clear();
		CardService.Connect.getService().getAllCardAdmins(card, new AsyncCallback<List<HuskyUser>>() {
			
			@Override
			public void onSuccess(List<HuskyUser> result) {
				HuskyUserListNavigation.this.cardUsers.addAll(result);
				for(HuskyUser user : result){
					adminPanel.add(new UserItem(user, huskyCardNavigation));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void setAllViewerUsers(){
		viewerMenu.addStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		viewerPanel.setVisible(true);
		adminPanel.setVisible(false);
		viewerPanel.clear();
		CardService.Connect.getService().getAllCardViewers(card, new AsyncCallback<List<HuskyUser>>() {
			
			@Override
			public void onSuccess(List<HuskyUser> result) {
				HuskyUserListNavigation.this.cardUsers.addAll(result);
				for(HuskyUser user : result){
					viewerPanel.add(new UserItem(user, huskyCardNavigation));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	public HuskyCard getCard() {
		return card;
	}

	public void setCard(HuskyCard card) {
		this.card = card;
	}

	public void setHuskyCardNavigation(HuskyCardNavigation huskyCardNavigation) {
		changeNav(adminMenu, IHuskyConstants.NAV_ADMIN);
		this.huskyCardNavigation = huskyCardNavigation;
	}

	public List<HuskyUser> getCardUsers() {
		return cardUsers;
	}

	public void setCardUsers(List<HuskyUser> cardUsers) {
		this.cardUsers = cardUsers;
	}

}
