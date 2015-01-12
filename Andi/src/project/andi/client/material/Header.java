package project.andi.client.material;

import project.andi.client.page.MaintenancePage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Header extends Composite {

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}
	
	@UiField Image imgLogo;
	@UiField Label lblName;
	
	private ImageResource logo;
	private String name;

	public Header() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getLogo() {
		return logo;
	}

	public void setLogo(ImageResource logo) {
		this.logo = logo;
		imgLogo.setResource(logo);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		lblName.setText(name);
	}
	
	@UiHandler("btnLogout")
	void onLogout(ClickEvent e){
		RootPanel.get().clear();
		RootPanel.get().add(new MaintenancePage());
	}
	
	

}
