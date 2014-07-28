package husky.wooof.com.client.ui;

import husky.wooof.com.shared.HuskyCard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyCardItem extends Composite {

	private static HuskyCardItemUiBinder uiBinder = GWT
			.create(HuskyCardItemUiBinder.class);

	interface HuskyCardItemUiBinder extends UiBinder<Widget, HuskyCardItem> {
	}

	@UiField Label lblCardName , lblDescription;
	
	
	public HuskyCardItem(HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		lblCardName.setText(card.getName());
		lblDescription.setText(card.getDescription());
	}

}
