package project.andi.client.page;

import java.util.List;

import project.andi.client.material.Couple;
import project.andi.client.material.MaterialLoader;
import project.andi.client.material.MaterialModal;
import project.andi.client.material.MaterialTextBox;
import project.andi.client.material.MaterialToast;
import project.andi.client.modal.ModalAddStory;
import project.andi.client.services.StoryService;
import project.andi.shared.Story;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaintenancePage extends Composite {

	private static MaintenancePageUiBinder uiBinder = GWT
			.create(MaintenancePageUiBinder.class);

	interface MaintenancePageUiBinder extends UiBinder<Widget, MaintenancePage> {
	}
	
	@UiField MaterialTextBox txtCode;
	@UiField HTMLPanel loginPanel, storyPanel;

	public MaintenancePage() {
		initWidget(uiBinder.createAndBindUi(this));
		getAllStories();
	}
	
	@UiHandler("btnLoadStory")
	void onLoadStory(ClickEvent e){
		if(!txtCode.getText().isEmpty()){
			loadStory(txtCode.getText());
		}else{
			MaterialToast.alert("Please enter story code.");
		}
		
	}
	
	@UiHandler("lblCreateStory")
	void onCreateStory(ClickEvent e){
		MaterialModal.showModal(true, new ModalAddStory(this));
		//new AndiDialog(new ModalAddStory(this),10, 40, 50);
	}
	
	public void loadStory(String code){
		MaterialLoader.showLoading(true, loginPanel);
		StoryService.Connect.getService().getStory(code, new AsyncCallback<Story>() {
			
			@Override
			public void onSuccess(Story result) {
				if(result!=null){
					RootPanel.get().clear();
					RootPanel.get().add(new MainPage(result, false));
				}else{
					MaterialToast.alert("Story not found");
				}
				MaterialLoader.showLoading(false);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.alert(caught.getMessage());
				MaterialLoader.showLoading(false);
			}
		});
	}

	private void getAllStories(){
		StoryService.Connect.getService().getAllStory(new AsyncCallback<List<Story>>() {
			
			@Override
			public void onSuccess(List<Story> result) {
				double i = 100;
				for(Story story : result){
					storyPanel.add(new Couple(MaintenancePage.this, story, i));
					i = i + 100;
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
