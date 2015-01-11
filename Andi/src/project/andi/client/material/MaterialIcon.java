package project.andi.client.material;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialIcon extends FocusPanel{

	private HTMLPanel panel = new HTMLPanel("");
	
	private String icon;
	private String fontSize;
	
	public MaterialIcon() {
	}

	public String getIcon() {
		return icon;
	}
	

	public void setIcon(String icon) {
		this.icon = icon;
		this.clear();
		panel = new  HTMLPanel("<i class='"+icon+"'></i>");
		this.add(panel);
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}
	
}
