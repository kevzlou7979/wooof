package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HuskyEditableLabel extends VerticalPanel{


	private String text;
	private HuskyTextBox txtBox = new HuskyTextBox();
	private Label lbl = new Label(); 
	
	public HuskyEditableLabel() {
		lbl.addStyleName(HuskyResources.INSTANCE.huskycss().huskyBasicDescription());
	}
	
	public void transformToTextBox(){
		this.clear();
		txtBox.setText(lbl.getText());
		this.add(txtBox);
	}
	
	public void transformToLabel(){
		this.clear();
		lbl.setText(txtBox.getText());
		this.add(lbl);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.clear();
		lbl.setText(text);
		this.add(lbl);
	}

	public HuskyTextBox getTxtBox() {
		return txtBox;
	}

	public void setTxtBox(HuskyTextBox txtBox) {
		this.txtBox = txtBox;
	}
	
	
}
