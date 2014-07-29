package husky.wooof.com.client.ui;

import husky.wooof.com.shared.HuskyCard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
	
	public HuskyCardItem(HuskyCard card, final double value) {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		
		Timer timer = new Timer()
        {
            @Override
            public void run()
            {
            	panel.addStyleName("panelAfter");
            	panel.getElement().setAttribute("style", "transition-delay: " + String.valueOf(value) + "s");
            }
        };

        timer.schedule(500);
		
		lblCardName.setText(card.getName());
		lblDescription.setText(card.getDescription());
		cardImage.setUrl(card.getCardImage());
	}

}
