package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.HuskyActionDialog;
import husky.wooof.com.client.ui.NoResultUtil;
import husky.wooof.com.client.ui.UserItem;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardInfoSidebar extends Composite {

	private static CardInfoSidebarUiBinder uiBinder = GWT.create(CardInfoSidebarUiBinder.class);

	interface CardInfoSidebarUiBinder extends UiBinder<Widget, CardInfoSidebar> {
	}

	private HuskyCard card;
	private HuskyUser user;
	@UiField Image imgCard, imgCardQrCode;
	@UiField Label lblCardName, lblNoHuskies, lblCardDesc;
	@UiField HTMLPanel adminPanel;
	@UiField CheckBox cbRealTimeBrowsing;
	
	public CardInfoSidebar(HuskyCardNavigation huskyCardNavigation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.card = huskyCardNavigation.getWorkspaceMain().getCard();
		this.user = huskyCardNavigation.getWorkspaceMain().getHuskyMain().getUser();
		if(!card.getCardImage().isEmpty()){
			imgCard.setUrl(card.getCardImage());
		}
		lblCardDesc.setText(card.getDescription());
		lblCardName.setText(card.getName());
		lblNoHuskies.setText(String.valueOf(card.getViewers().size()));
		imgCardQrCode.setUrl("http://chart.apis.google.com/chart?cht=qr&chs=100x100&chl=" + String.valueOf(card.getId()));
		setAllAdminUsers();
	}
	
	public void setAllAdminUsers() {
		adminPanel.clear();
		CardService.Connect.getService().getAllCardAdmins(card, new AsyncCallback<List<HuskyUser>>() {

			@Override
			public void onSuccess(List<HuskyUser> result) {
				if(!result.isEmpty()){
					for (HuskyUser user : result) {
						adminPanel.add(new UserItem(user, CardInfoSidebar.this, true));
					}
				}else{
					new NoResultUtil(HuskyResources.INSTANCE.ic_gray_user(), "No Recent Users", adminPanel);
				}
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@UiHandler("btnUnsub")
	void onUnSub(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_WARN, "Are you sure you want to unsubscribe to this card?", "Oops wait a second...");
	}
	
	public void unsubscribeFromCard(){
		
		CardService.Connect.getService().removeUserFromCard(user, card, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				RootPanel.get().clear();
				RootPanel.get().add(new HuskyMain(user));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public CheckBox getCbRealTimeBrowsing() {
		return cbRealTimeBrowsing;
	}

	public void setCbRealTimeBrowsing(CheckBox cbRealTimeBrowsing) {
		this.cbRealTimeBrowsing = cbRealTimeBrowsing;
	}
	
	
}
