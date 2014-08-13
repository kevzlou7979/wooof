package husky.wooof.com.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HuskyMaintenance extends Composite {

	private static HuskyMaintenanceUiBinder uiBinder = GWT
			.create(HuskyMaintenanceUiBinder.class);

	interface HuskyMaintenanceUiBinder extends
			UiBinder<Widget, HuskyMaintenance> {
	}

	public HuskyMaintenance() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
