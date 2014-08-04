package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;

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

	@UiField HTMLPanel panel;
	private HuskyCardNavigation cardNavigation;
	
	public WorkspaceMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		cardNavigation = new HuskyCardNavigation(huskyMain);
		cardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().cardNavigation());
		panel.add(cardNavigation);
	}



}
