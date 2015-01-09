package project.andi.client.page;

import project.andi.client.modal.AndiDialog;
import project.andi.client.modal.ModalAddStory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaintenancePage extends Composite {

	private static MaintenancePageUiBinder uiBinder = GWT
			.create(MaintenancePageUiBinder.class);

	interface MaintenancePageUiBinder extends UiBinder<Widget, MaintenancePage> {
	}

	public MaintenancePage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("logo")
	void onLogo(ClickEvent e){
		RootPanel.get().clear();
		RootPanel.get().add(new MainPage());
	}
	
	@UiHandler("lblCreateStory")
	void onCreateStory(ClickEvent e){
		new AndiDialog(new ModalAddStory(),10, 40, 50);
	}

}
