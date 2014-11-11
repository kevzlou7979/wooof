package husky.wooof.com.client;

import husky.wooof.com.client.dialog.CreateMain;
import husky.wooof.com.client.dialog.HuskyDialog;
import husky.wooof.com.client.main.AccountMain;
import husky.wooof.com.client.main.CardsMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.navigation.HuskyNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HuskyMain extends Composite {

	private static HuskyMainUiBinder uiBinder = GWT.create(HuskyMainUiBinder.class);

	interface HuskyMainUiBinder extends UiBinder<Widget, HuskyMain> {
	}

	@UiField
	HTMLPanel huskyNavigationPanel, huskyMainPanel;
	@UiField 
	Image btnMobileNav, btnMobileCardNav;

	private HuskyNavigation huskyNavigation;

	private CardsMain cardsMain;
	private CreateMain createMain;
	private AccountMain accountMain;
	private HuskyCardNavigation huskyCardNavigation;

	private HuskyDialog huskyDialog;
	private HuskyUser user;
	private boolean isHidden = true;

	public HuskyMain(HuskyUser user) {
		initWidget(uiBinder.createAndBindUi(this));

		this.user = user;

		cardsMain = new CardsMain(this);
		createMain = new CreateMain(this);
		accountMain = new AccountMain(this);

		huskyNavigation = new HuskyNavigation(this);
		huskyNavigationPanel.add(huskyNavigation);
		
		showMobileCardNav(false);
	}

	public HuskyNavigation getHuskyNavigation() {
		return huskyNavigation;
	}

	public void setHuskyNavigation(HuskyNavigation huskyNavigation) {
		this.huskyNavigation = huskyNavigation;
	}

	public CardsMain getCardsMain() {
		return cardsMain;
	}

	public void setCardsMain(CardsMain cardsMain) {
		this.cardsMain = cardsMain;
	}

	public HTMLPanel getHuskyMainPanel() {
		return huskyMainPanel;
	}

	public void setHuskyMainPanel(HTMLPanel huskyMainPanel) {
		this.huskyMainPanel = huskyMainPanel;
	}

	public CreateMain getCreateMain() {
		return createMain;
	}

	public void setCreateMain(CreateMain createMain) {
		this.createMain = createMain;
	}

	public HuskyUser getUser() {
		return user;
	}

	public void setUser(HuskyUser user) {
		this.user = user;
	}

	public HuskyDialog getHuskyDialog() {
		return huskyDialog;
	}

	public void setHuskyDialog(HuskyDialog huskyDialog) {
		this.huskyDialog = huskyDialog;
	}

	public AccountMain getAccountMain() {
		return accountMain;
	}

	public void setAccountMain(AccountMain accountMain) {
		this.accountMain = accountMain;
	}

	public void minimizeSideBar(boolean isMinimize) {
		if (isMinimize) {
			huskyNavigationPanel.getElement().getStyle().setLeft(-250, Unit.PX);;
			huskyMainPanel.getElement().getStyle().setPaddingLeft(0, Unit.PX);
		}
		else {
			huskyNavigationPanel.getElement().getStyle().setLeft(0, Unit.PX);
			huskyMainPanel.getElement().getStyle().setPaddingLeft(300, Unit.PX);
		}
	}
	
	@UiHandler("btnMobileNav")
	void onOpenSideBar(ClickEvent e){
		if(isHidden){
			huskyNavigationPanel.getElement().getStyle().setLeft(0, Unit.PX);
			isHidden = false;
		}else{
			huskyNavigationPanel.getElement().getStyle().setLeft(-250, Unit.PX);;
			isHidden = true;
		}
		
	}
	
	@UiHandler("btnMobileCardNav")
	void onOpenCardSideBar(ClickEvent e){
		if(isHidden){
			huskyCardNavigation.getElement().setAttribute("style", "right: 0px !important;");
			huskyCardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().transition());
			huskyCardNavigation.getNavContent().setVisible(true);
			isHidden = false;
		}else{
			huskyCardNavigation.getElement().setAttribute("style", "right: -75px !important;");
			huskyCardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().transition());
			huskyCardNavigation.getNavContent().setVisible(false);
			isHidden = true;
		}
		
	}

	public void showMobileCardNav(boolean isShow){
		if(isShow){
			btnMobileCardNav.setVisible(true);
			btnMobileNav.setVisible(false);
		}else{
			btnMobileCardNav.setVisible(false);
			btnMobileNav.setVisible(true);
		}
	}

	public HuskyCardNavigation getHuskyCardNavigation() {
		return huskyCardNavigation;
	}

	public void setHuskyCardNavigation(HuskyCardNavigation huskyCardNavigation) {
		this.huskyCardNavigation = huskyCardNavigation;
	}
}
