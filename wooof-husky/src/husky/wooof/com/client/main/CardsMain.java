package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.dialog.CreateMain;
import husky.wooof.com.client.dialog.HuskyDialog;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.HuskyCardItem;
import husky.wooof.com.client.ui.HuskyLoading;
import husky.wooof.com.client.ui.NoResultUtil;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardsMain extends Composite {

	private static CardsMainUiBinder uiBinder = GWT.create(CardsMainUiBinder.class);

	interface CardsMainUiBinder extends UiBinder<Widget, CardsMain> {
	}

	@UiField
	HTMLPanel panel, cardsPanel;

	private HuskyMain huskyMain;

	public CardsMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		onLoadAllCards();
	}

	public void onLoadAllCards() {
		cardsPanel.clear();
		HuskyLoading.showLoading(true, cardsPanel, "Getting your cards", 30, IHuskyConstants.LOADING_CIRCLE);
		CardService.Connect.getService().getAllCards(huskyMain.getUser(), new AsyncCallback<List<HuskyCard>>() {

			@Override
			public void onSuccess(List<HuskyCard> result) {
				HuskyLoading.showLoading(false);
				if(!result.isEmpty()){
					double i = 100;
					for (HuskyCard card : result) {
						cardsPanel.add(new HuskyCardItem(card, i, CardsMain.this));
						i = i + 100;
					}
				}else{
					NoResultUtil noResult = new NoResultUtil(HuskyResources.INSTANCE.ic_no_cards(), "Please add your first Card in order to feel the Husky Experience", cardsPanel);
					noResult.getElement().getStyle().setMarginLeft(-50, Unit.PX);
					cardsPanel.add(noResult);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				HuskyLoading.showLoading(false);
			}
		});
	}

	@UiHandler("btnAdd")
	void onAddCard(ClickEvent e) {
		HuskyDialog dialog = new HuskyDialog(new CreateMain(huskyMain),10, 40, 50);
		dialog.setWidth("500px");
		dialog.setModal(true);
		dialog.setGlassEnabled(true);
		dialog.center();
		dialog.show();
		huskyMain.setHuskyDialog(dialog);
	}

	public HuskyMain getHuskyMain() {
		return huskyMain;
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}

}
