package project.andi.client.modal;

import project.andi.client.material.AndiTextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ModalAddStory extends Composite {

	private static ModalAddStoryUiBinder uiBinder = GWT
			.create(ModalAddStoryUiBinder.class);

	interface ModalAddStoryUiBinder extends UiBinder<Widget, ModalAddStory> {
	}
	
	@UiField AndiTextBox lblTitle;

	public ModalAddStory() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
