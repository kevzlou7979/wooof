package project.andi.client.modal;

import project.andi.client.material.MaterialTextBox;
import project.andi.client.material.MaterialToast;
import project.andi.client.page.MainPage;
import project.andi.client.services.StoryService;
import project.andi.shared.Story;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ModalLogin extends Composite {

	private static ModalLoginUiBinder uiBinder = GWT
			.create(ModalLoginUiBinder.class);

	interface ModalLoginUiBinder extends UiBinder<Widget, ModalLogin> {
	}

	@UiField MaterialTextBox txtCode, txtPassword;
	private MainPage mainPage;
	
	public ModalLogin(MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.mainPage = mainPage;
	}

	@UiHandler("lblLogin")
	void onLogin(ClickEvent e){
		StoryService.Connect.getService().login(txtCode.getText(), txtPassword.getText(), new AsyncCallback<Story>() {
			
			@Override
			public void onSuccess(Story result) {
				if(mainPage.getStory().getCode().equals(txtCode.getText())){
					if(result!=null){
						RootPanel.get().clear();
						RootPanel.get().add(new MainPage(result, true));
						MaterialToast.alert("Successfully Logged in as owner.");
					}else{
						MaterialToast.alert("Incorrect story password.");
					}
				}else{
					MaterialToast.alert("Please Enter the correct code for this story.");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.alert(caught.getMessage());
			}
		});
	}
}
