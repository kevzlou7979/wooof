package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.ui.HuskyLoading;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CardsMain extends Composite {

	private static CardsMainUiBinder uiBinder = GWT
			.create(CardsMainUiBinder.class);

	interface CardsMainUiBinder extends UiBinder<Widget, CardsMain> {
	}

	public CardsMain(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		HuskyLoading.showLoading(true);
	}

}
