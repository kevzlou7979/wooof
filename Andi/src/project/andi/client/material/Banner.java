package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Banner extends Composite {

	private static BannerUiBinder uiBinder = GWT.create(BannerUiBinder.class);

	interface BannerUiBinder extends UiBinder<Widget, Banner> {
	}

	@UiField HTMLPanel soundCloudPanel;
	
	public Banner() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
}
