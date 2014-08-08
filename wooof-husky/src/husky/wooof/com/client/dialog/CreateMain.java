package husky.wooof.com.client.dialog;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.HuskyLoading;
import husky.wooof.com.client.ui.HuskyMessage;
import husky.wooof.com.client.ui.HuskyTextArea;
import husky.wooof.com.client.ui.HuskyTextBox;
import husky.wooof.com.client.ui.HuskyUploadArea;
import husky.wooof.com.shared.FieldVerifier;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateMain extends Composite {

	private static CreateMainUiBinder uiBinder = GWT
			.create(CreateMainUiBinder.class);

	interface CreateMainUiBinder extends UiBinder<Widget, CreateMain> {
	}
	
	@UiField HTMLPanel panel, basicInfoPanel, messagePanel;
	@UiField HuskyUploadArea cardPhoto;
	@UiField HuskyTextBox txtCardName;
	@UiField HuskyTextArea txtCardDescription;
	
	private HuskyMain huskyMain;
	private HuskyCard card;
	private List<HuskyUser> users = new ArrayList<HuskyUser>();
	
	public CreateMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
	}
	
	@UiHandler("btnCreateCard")
	void onCreateCard(ClickEvent e){
		if(FieldVerifier.isValidFields(basicInfoPanel, messagePanel)){
			users.clear();
			users.add(huskyMain.getUser());
			card = new HuskyCard(txtCardName.getText(), txtCardDescription.getText());
			card.setCardImage(cardPhoto.getCardImage().getUrl());
			basicInfoPanel.setVisible(false);
			huskyMain.getHuskyDialog().hide();
			HuskyLoading.showLoading(true, panel, "Creating Card", 30);
			CardService.Connect.getService().saveCard(card,users, new AsyncCallback<HuskyCard>() {
				
				@Override
				public void onSuccess(final HuskyCard result) {
					Timer timer = new Timer()
			        {
			            @Override
			            public void run()
			            {
			            	HuskyLoading.showLoading(false);
			            	huskyMain.setCreateMain(new CreateMain(huskyMain));
			            	huskyMain.getCardsMain().onLoadAllCards();
			            	huskyMain.getHuskyMainPanel().clear();
			            	huskyMain.getHuskyMainPanel().add(new WorkspaceMain(huskyMain, result));
			            	//TODO Load the Workspace
			            	Window.alert("Workspace Loaded");
			            }
			        };
	
			        timer.schedule(1000);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					basicInfoPanel.setVisible(true);
					HuskyLoading.showLoading(false);
					HuskyMessage.showMessage(false, messagePanel, caught.getMessage());
				}
			});
		}
	}
}
