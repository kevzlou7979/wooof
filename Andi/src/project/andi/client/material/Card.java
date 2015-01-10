package project.andi.client.material;

import project.andi.shared.StoryItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Card extends Composite {

	private static CardUiBinder uiBinder = GWT.create(CardUiBinder.class);

	interface CardUiBinder extends UiBinder<Widget, Card> {
	}
	
	@UiField Label lblTitle, lblIntroduction, lblContent, lblPlace, lblTime;
	@UiField Image imgCard;
	
	@SuppressWarnings("deprecation")
	public Card(StoryItem item) {
		initWidget(uiBinder.createAndBindUi(this));
		lblTitle.setText(item.getTitle());
		lblIntroduction.setText(item.getIntroduction());
		lblContent.setText(item.getContent());
		lblPlace.setText(item.getPlace());
		lblTime.setText(DateTimeFormat.getMediumDateTimeFormat().format(item.getCreationDate()));
		imgCard.setUrl(item.getImageUrl());
	}

}
