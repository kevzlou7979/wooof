package project.andi.client.modal;

import project.andi.client.material.AndiTextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ModalAddStoryItem extends Composite {

	private static ModalAddStoryUiBinder uiBinder = GWT
			.create(ModalAddStoryUiBinder.class);

	interface ModalAddStoryUiBinder extends UiBinder<Widget, ModalAddStoryItem> {
	}
	
	@UiField AndiTextBox lblTitle;

	public ModalAddStoryItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
