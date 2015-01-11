package project.andi.client.material;

import project.andi.client.modal.ModalAddStoryItem;
import project.andi.client.page.MainPage;
import project.andi.shared.Story;
import project.andi.shared.StoryItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
	
	private StoryItem item;
	private Story story;
	private MainPage mainPage;
	
	@SuppressWarnings("deprecation")
	public Card(Story story, StoryItem item, MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.item = item;
		this.story = story;
		this.mainPage = mainPage;
		lblTitle.setText(item.getTitle());
		lblIntroduction.setText(item.getIntroduction());
		lblContent.setText(item.getContent());
		lblPlace.setText(item.getPlace());
		lblTime.setText(DateTimeFormat.getMediumDateTimeFormat().format(item.getCreationDate()));
		imgCard.setUrl(item.getImageUrl());
	}

	@UiHandler("btnAction")
	void onEdit(ClickEvent e){
		MaterialModal.showModal(true, new ModalAddStoryItem(story, item, mainPage));
	}
	
}
