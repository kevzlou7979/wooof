package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AndiTextBox extends Composite {

	private static AndiTextBoxUiBinder uiBinder = GWT
			.create(AndiTextBoxUiBinder.class);

	interface AndiTextBoxUiBinder extends UiBinder<Widget, AndiTextBox> {
	}
	
	private String text;
	private String placeholder;
	
	@UiField Label lblName;	
	
	public AndiTextBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		lblName.setText(placeholder);
	}

}
