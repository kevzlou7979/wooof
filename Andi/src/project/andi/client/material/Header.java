package project.andi.client.material;

import project.andi.client.modal.ModalLogin;
import project.andi.client.page.MainPage;
import project.andi.client.page.MaintenancePage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
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
	@UiField MaterialIcon btnLogin;
	
	private boolean isHidden = false;
	private ImageResource logo;
	private String name;
	private MainPage mainPage;
	
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
	
	@UiHandler("btnLogin")
	void onLogin(ClickEvent e){
		MaterialModal.showModal(true, new ModalLogin(mainPage));
	}

	public MainPage getMainPage() {
		return mainPage;
	}

	public void setMainPage(MainPage mainPage) {
		this.mainPage = mainPage;
		if(mainPage.isOwner()){
			btnLogin.removeFromParent();
		}
	}
	
	@UiHandler("btnMenu")
	void onMobileMenu(ClickEvent e){
		if(isHidden){
			getMainPage().getSideBar().getPanel().getElement().getStyle().setLeft(-300, Unit.PCT);
			isHidden = false;
		}else{
			getMainPage().getSideBar().getPanel().getElement().getStyle().setLeft(0, Unit.PCT);
			isHidden = true;
		}
	}
	
	

}
