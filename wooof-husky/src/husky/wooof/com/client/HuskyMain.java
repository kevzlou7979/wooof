package husky.wooof.com.client;

import husky.wooof.com.client.dialog.CreateMain;
import husky.wooof.com.client.dialog.HuskyDialog;
import husky.wooof.com.client.main.AccountMain;
import husky.wooof.com.client.main.CardsMain;
import husky.wooof.com.client.navigation.HuskyNavigation;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyMain extends Composite {

	private static HuskyMainUiBinder uiBinder = GWT
			.create(HuskyMainUiBinder.class);

	interface HuskyMainUiBinder extends UiBinder<Widget, HuskyMain> {
	}

	@UiField HTMLPanel huskyNavigationPanel, huskyMainPanel;
	
	private HuskyNavigation huskyNavigation;
	
	private CardsMain cardsMain;
	private CreateMain createMain;
	private AccountMain accountMain;
	
	private HuskyDialog huskyDialog;
	private HuskyUser user;
	
	public HuskyMain(HuskyUser user) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		
		cardsMain = new CardsMain(this);
		createMain = new CreateMain(this);
		accountMain = new AccountMain(this);
		
		huskyNavigation = new HuskyNavigation(this);
		huskyNavigationPanel.add(huskyNavigation);
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

}
