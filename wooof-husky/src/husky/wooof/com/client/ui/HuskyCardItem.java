package husky.wooof.com.client.ui;

import husky.wooof.com.client.main.CardsMain;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.shared.HuskyCard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyCardItem extends Composite {

	private static HuskyCardItemUiBinder uiBinder = GWT
			.create(HuskyCardItemUiBinder.class);

	interface HuskyCardItemUiBinder extends UiBinder<Widget, HuskyCardItem> {
	}

	@UiField Label lblCardName , lblDescription;
	@UiField Image cardImage;
	@UiField HTMLPanel panel;
	
	private CardsMain cardsMain;
	private HuskyCard card;
	
	public HuskyCardItem(HuskyCard card, final double value, CardsMain cardsMain) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.cardsMain = cardsMain;
		this.card = card;
		
		Timer timer = new Timer()
        {
            @Override
            public void run()
            {
            	panel.addStyleName("panelAfter");
            	panel.getElement().setAttribute("style", "transition-delay: " + String.valueOf(value) + "ms");
            }
        };

        timer.schedule(500);
		
		lblCardName.setText(card.getName());
		lblDescription.setText(card.getDescription());
		cardImage.setWidth("100%");
		cardImage.setHeight("100%");
		cardImage.setUrl(card.getCardImage());
	}

	@UiHandler("lblCardName")
	void onClickCardName(ClickEvent e){
		cardsMain.getHuskyMain().getHuskyMainPanel().clear();
		cardsMain.getHuskyMain().getHuskyMainPanel().add(new WorkspaceMain(cardsMain.getHuskyMain(), card));
	}
	
}
