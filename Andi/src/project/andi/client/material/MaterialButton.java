	package project.andi.client.material;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialButton extends FocusPanel {

	private HTMLPanel panel;

	private String text = "";
	private String flat = "";


	public MaterialButton() {
		
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.clear();
		panel = new HTMLPanel("<button class='btn waves-effect waves-light pink darken-4 gwtMaterialDesign' type='submit' name='action'>" + text + "</button>");
		this.add(panel);
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
		this.clear();
		panel = new HTMLPanel("<a href='#' class='waves-effect btn-flat modal-close'>"+flat+"</a>");
		this.add(panel);
	}

	
}
