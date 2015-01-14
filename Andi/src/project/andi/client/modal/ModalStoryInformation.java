package project.andi.client.modal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ModalStoryInformation extends Composite {

	private static ModalStoryInformationUiBinder uiBinder = GWT
			.create(ModalStoryInformationUiBinder.class);

	interface ModalStoryInformationUiBinder extends
			UiBinder<Widget, ModalStoryInformation> {
	}

	public ModalStoryInformation() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
