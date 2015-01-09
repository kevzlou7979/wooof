package project.andi.client.material;

import project.andi.client.modal.AndiDialog;
import project.andi.client.modal.ModalAddStoryItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FloatingButton extends Composite {

	private static FloatingButtonUiBinder uiBinder = GWT
			.create(FloatingButtonUiBinder.class);

	interface FloatingButtonUiBinder extends UiBinder<Widget, FloatingButton> {
	}

	public FloatingButton() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("btnFloating")
	void onCreateStory(ClickEvent e){
		new AndiDialog(new ModalAddStoryItem(),10, 40, 50);
	}


}
