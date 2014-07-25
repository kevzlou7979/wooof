package husky.wooof.com.client;

import husky.wooof.com.client.main.CardsMain;
import husky.wooof.com.client.main.CreateMain;
import husky.wooof.com.client.navigation.HuskyNavigation;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
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
	
	public HuskyMain(HuskyUser user) {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		
		cardsMain = new CardsMain(this);
		createMain = new CreateMain(this);
		
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

	
}