package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.shared.HuskyCard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceMain extends Composite{

	private static WorkspaceMainUiBinder uiBinder = GWT
			.create(WorkspaceMainUiBinder.class);

	interface WorkspaceMainUiBinder extends UiBinder<Widget, WorkspaceMain> {
	}

	@UiField HTMLPanel panel, activeUsersPanel;
	
	
	private HuskyCardNavigation cardNavigation;
	private HuskyCard card;
	private HuskyMain huskyMain;
	
	public WorkspaceMain(HuskyMain huskyMain, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		this.card = card;
		cardNavigation = new HuskyCardNavigation(this);
		cardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().cardNavigation());
		panel.add(cardNavigation);
	}

	public HuskyCard getCard() {
		return card;
	}

	public void setCard(HuskyCard card) {
		this.card = card;
	}

	public HuskyMain getHuskyMain() {
		return huskyMain;
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}

	public HTMLPanel getActiveUsersPanel() {
		return activeUsersPanel;
	}

	public void setActiveUsersPanel(HTMLPanel activeUsersPanel) {
		this.activeUsersPanel = activeUsersPanel;
	}



}
