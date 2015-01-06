package project.andi.client.material;

import com.google.gwt.core.client.GWT;
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

	@UiField TextBox txtBox;
	@UiField Label lblName;
	
	private String label;
	
	public MaterialTextBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		lblName.setText(label);
	}

	public TextBox getTxtBox() {
		return txtBox;
	}

	public void setTxtBox(TextBox txtBox) {
		this.txtBox = txtBox;
	}
	
	

}
