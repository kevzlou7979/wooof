package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FloatingButton extends Composite {

	private static FloatingButtonUiBinder uiBinder = GWT
			.create(FloatingButtonUiBinder.class);

	interface FloatingButtonUiBinder extends UiBinder<Widget, FloatingButton> {
	}

	@UiField Label btnFloat;
	private String color;
	
	public FloatingButton() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		btnFloat.getElement().getStyle().setBackgroundColor(color);
	}

}
