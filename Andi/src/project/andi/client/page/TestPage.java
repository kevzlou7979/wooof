package project.andi.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TestPage extends Composite {

	private static TestPageUiBinder uiBinder = GWT
			.create(TestPageUiBinder.class);

	interface TestPageUiBinder extends UiBinder<Widget, TestPage> {
	}

	public TestPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
