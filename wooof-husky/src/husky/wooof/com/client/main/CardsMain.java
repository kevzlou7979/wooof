package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.HuskyCardItem;
import husky.wooof.com.client.ui.HuskyLoading;
import husky.wooof.com.shared.HuskyCard;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardsMain extends Composite {

	private static CardsMainUiBinder uiBinder = GWT
			.create(CardsMainUiBinder.class);

	interface CardsMainUiBinder extends UiBinder<Widget, CardsMain> {
	}

	@UiField HTMLPanel panel, cardsPanel;
	
	private HuskyMain huskyMain;
	
	public CardsMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		onLoadAllCards();
	}
	
	public void onLoadAllCards(){
		cardsPanel.clear();
		HuskyLoading.showLoading(true, panel, "Getting all cards", 30);
		CardService.Connect.getService().getAllCards(huskyMain.getUser(), new AsyncCallback<List<HuskyCard>>() {
			
			@Override
			public void onSuccess(List<HuskyCard> result) {
				double i = 0.1;
				for(HuskyCard card : result){
					cardsPanel.add(new HuskyCardItem(card, i));
					i++;
				}
				HuskyLoading.showLoading(false);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				HuskyLoading.showLoading(false);
			}
		});
	}

}
