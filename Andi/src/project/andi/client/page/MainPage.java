package project.andi.client.page;

import java.util.List;

import project.andi.client.material.Card;
import project.andi.client.material.MaterialLoader;
import project.andi.client.material.MaterialModal;
import project.andi.client.material.MaterialToast;
import project.andi.client.modal.ModalAddStoryItem;
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

public class MainPage extends Composite {

	private static MainPageUiBinder uiBinder = GWT
			.create(MainPageUiBinder.class);

	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}

	@UiField HTMLPanel cardPanel;
	
	private Story story;
	
	public MainPage(Story story) {
		initWidget(uiBinder.createAndBindUi(this));
		this.setStory(story);
		getAllStoryItem(story);
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	@UiHandler("btnAddStoryItem")
	void onAddStoryItem(ClickEvent e){
		MaterialModal.showModal(true, new ModalAddStoryItem(story, this));
		//andiDialog = new AndiDialog(new ModalAddStoryItem(story, this),10, 40, 50);
	}

	
	public void getAllStoryItem(final Story story){
		MaterialLoader.showLoading(true, cardPanel);
		StoryService.Connect.getService().getAllStoryItems(story.getId(), new AsyncCallback<List<StoryItem>>() {
			
			@Override
			public void onSuccess(List<StoryItem> result) {
				cardPanel.clear();
				if(!result.isEmpty()){
					for(StoryItem item : result){
						cardPanel.add(new Card(story, item, MainPage.this));
					}
				}else{
					MaterialToast.alert("No Story Item Found");
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.alert(caught.getMessage());
			}
		});
	}
	
}
