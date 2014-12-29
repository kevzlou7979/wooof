package project.andi.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaintenancePage extends Composite {

	private static MaintenancePageUiBinder uiBinder = GWT
			.create(MaintenancePageUiBinder.class);

	interface MaintenancePageUiBinder extends UiBinder<Widget, MaintenancePage> {
	}

	public MaintenancePage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
