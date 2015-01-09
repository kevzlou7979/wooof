package project.andi.client.modal;

import project.andi.client.material.AndiLoader;
import project.andi.client.material.AndiTextBox;
import project.andi.client.material.AndiToast;
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

	@UiField AndiTextBox txtTitle, txtDescription, txtCode, txtPassword;
	@UiField HTMLPanel panel;
	
	public ModalAddStory() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("lblCreate")
	void onCreateStory(ClickEvent e){
		AndiLoader.showLoading(true, panel);
		StoryService.Connect.getService().createStory(new Story(txtCode.getText(), txtPassword.getText(), txtTitle.getText(), txtDescription.getText()), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				AndiToast.alert("Successfully Created your Story");
				AndiLoader.showLoading(false);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				AndiToast.alert(caught.getMessage());
				AndiLoader.showLoading(false);
			}
		});
		
	}
	
	
	
}
