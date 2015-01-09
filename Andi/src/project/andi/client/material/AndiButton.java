package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AndiButton extends Composite {

	private static AndiButtonUiBinder uiBinder = GWT
			.create(AndiButtonUiBinder.class);

	interface AndiButtonUiBinder extends UiBinder<Widget, AndiButton> {
	}
	
	@UiField Button btnSubmit;
	private ActionButton action;
	private String text;
	private String color;
	
	public AndiButton() {
		initWidget(uiBinder.createAndBindUi(this));
		btnSubmit.removeStyleName("gwt-Button");
	}

	public AndiButton(ActionButton action) {
		initWidget(uiBinder.createAndBindUi(this));
		this.action = action;
		btnSubmit.removeStyleName("gwt-Button");
	}
	
	@UiHandler("btnSubmit")
	void onSubmit(ClickEvent e){
		switch (action) {
		case SHOW_STORY:
			
			break;

		default:
			break;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		btnSubmit.setText(text);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		btnSubmit.addStyleName(color);
	}

}
