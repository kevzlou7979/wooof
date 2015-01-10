package project.andi.client.modal;

import project.andi.client.material.MaterialLoader;
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
	@UiField HTMLPanel panel;
	
	private Story story;
	private MainPage mainPage;
	
	public ModalAddStoryItem(Story story, MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.story = story;
		this.mainPage = mainPage;
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
			StoryService.Connect.getService().createStoryItem(new StoryItem(txtTitle.getText(), txtIntroduction.getText(), txtImageUrl.getText(), areaContent.getText(),txtPlace.getText(), story.getId()), new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					MaterialToast.alert("Successfully saved item");
					MaterialLoader.showLoading(false);
					mainPage.getAndiDialog().hide();
					mainPage.getAllStoryItem(story);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					MaterialToast.alert(caught.getMessage());
					MaterialLoader.showLoading(false);
				}
			});
		}
	}

}
