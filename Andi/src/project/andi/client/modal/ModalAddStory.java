package project.andi.client.modal;

import project.andi.client.material.MaterialLoader;
import project.andi.client.material.MaterialTextBox;
import project.andi.client.material.MaterialToast;
import project.andi.client.page.MaintenancePage;
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
import com.google.gwt.user.client.ui.Widget;

public class ModalAddStory extends Composite {

	private static ModalAddStoryUiBinder uiBinder = GWT
			.create(ModalAddStoryUiBinder.class);

	interface ModalAddStoryUiBinder extends UiBinder<Widget, ModalAddStory> {
	}

	@UiField MaterialTextBox txtTitle, txtDescription, txtCode, txtPassword;
	@UiField HTMLPanel panel;
	
	private MaintenancePage maintenancePage;
	
	
	
	public ModalAddStory(MaintenancePage maintenancePage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.maintenancePage = maintenancePage;
	}

	@UiHandler("lblCreate")
	void onCreateStory(ClickEvent e){
		boolean isReady = true;
		if(txtTitle.getText() == null ||  txtTitle.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Story Title is empty");
		}
		if(txtDescription.getText() ==null || txtDescription.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Story Description is empty");
		}
		if(txtCode.getText() == null || txtCode.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Story Code is empty");
		}
		if(txtPassword.getText() == null || txtPassword.getText().isEmpty()){
			isReady = false;
			MaterialToast.alert("Story Password is empty");
		}
		if(isReady){
			MaterialLoader.showLoading(true, panel);
			StoryService.Connect.getService().createStory(new Story(txtCode.getText(), txtPassword.getText(), txtTitle.getText(), txtDescription.getText()), new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					MaterialToast.alert("Successfully Created your Story");
					MaterialLoader.showLoading(false);
					maintenancePage.loadStory(txtCode.getText());
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
