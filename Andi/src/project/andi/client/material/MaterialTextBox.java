package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTextBox extends Composite {

	private static MaterialTextBoxUiBinder uiBinder = GWT
			.create(MaterialTextBoxUiBinder.class);

	interface MaterialTextBoxUiBinder extends UiBinder<Widget, MaterialTextBox> {
	}


	private String placeholder;
	private String type;
	
	@UiField Label lblName;	
	@UiField TextBox txtBox;
	
	public MaterialTextBox() {
		initWidget(uiBinder.createAndBindUi(this));
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand () {
	        public void execute () {
	        	txtBox.setFocus(true);
	        }
	    });
		
	}

	public String getText() {
		return txtBox.getText();
	}

	public void setText(String text) {
		txtBox.setText(text);
		
	}

	public String getPlaceholder() {
		return placeholder;
		
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		lblName.setText(placeholder);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		txtBox.getElement().setAttribute("type", type);
	}

}
