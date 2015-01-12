package project.andi.client.modal;

import project.andi.client.material.MaterialDatePicker;
import project.andi.client.material.MaterialLoader;
import project.andi.client.material.MaterialModal;
import project.andi.client.material.MaterialTextArea;
import project.andi.client.material.MaterialTextBox;
import project.andi.client.material.MaterialToast;
import project.andi.client.page.MainPage;
import project.andi.client.services.StoryService;
import project.andi.shared.Story;
import project.andi.shared.StoryItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ModalAddStoryItem extends Composite {

	private static ModalAddStoryUiBinder uiBinder = GWT
			.create(ModalAddStoryUiBinder.class);

	interface ModalAddStoryUiBinder extends UiBinder<Widget, ModalAddStoryItem> {
	}
	
	@UiField MaterialTextBox txtTitle, txtIntroduction, txtImageUrl, txtPlace;
	@UiField MaterialTextArea areaContent;
	@UiField MaterialDatePicker dpCreationDate;
	@UiField HTMLPanel panel;
	
	private Story story;
	private MainPage mainPage;
	private StoryItem storyItem = new StoryItem();
	
	public ModalAddStoryItem(Story story, MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.story = story;
		this.mainPage = mainPage;
	}
	
	/**
	 * This is for edition of existing story item
	 * @param storyItem
	 * @param mainPage
	 */
	public ModalAddStoryItem(Story story,StoryItem storyItem, MainPage mainPage){
		initWidget(uiBinder.createAndBindUi(this));
		this.storyItem = storyItem;
		this.story = story;
		this.mainPage = mainPage;
		txtTitle.setText(storyItem.getTitle());
		txtIntroduction.setText(storyItem.getIntroduction());
		areaContent.setText(storyItem.getContent());
		txtImageUrl.setText(storyItem.getImageUrl());
		txtPlace.setText(storyItem.getPlace());
		dpCreationDate.setDate(storyItem.getCreationDate());
	}
	
	@UiHandler("btnCreateStoryItem")
	void onCreateStoryItem(ClickEvent e){
		boolean isReady = true;
		
		if(txtTitle.getText() == null ||  txtTitle.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Title is empty");
		}
		if(txtIntroduction.getText() ==null || txtIntroduction.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Introduction is empty");
		}
		if(txtImageUrl.getText() == null || txtImageUrl.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Image URL is empty");
		}
		if(areaContent.getText() == null || areaContent.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Content is empty");
		}
		if(isReady){
			MaterialLoader.showLoading(true, panel);
			
			storyItem.setTitle(txtTitle.getText());
			storyItem.setIntroduction(txtIntroduction.getText());
			storyItem.setContent(areaContent.getText());
			storyItem.setImageUrl(txtImageUrl.getText());
			
			try {
				storyItem.setCreationDate(dpCreationDate.getPickerDate());
			} catch (IllegalArgumentException exception) {
				storyItem.setCreationDate(dpCreationDate.getDate());
			}
			storyItem.setPlace(txtPlace.getText());
			storyItem.setStoryId(story.getId());
			
			StoryService.Connect.getService().createStoryItem(storyItem, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					MaterialToast.alert("Successfully saved item");
					MaterialLoader.showLoading(false);
					mainPage.forceAddItem(storyItem);
					//mainPage.getAllStoryItem(story);
					
					MaterialModal.closeModal();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					MaterialToast.alert(caught.getMessage());
					MaterialLoader.showLoading(false);
				}
			});
		}
	}
	
	@UiHandler("lblCancel")
	void onCancel(ClickEvent e){
		MaterialModal.closeModal();
	}

}
