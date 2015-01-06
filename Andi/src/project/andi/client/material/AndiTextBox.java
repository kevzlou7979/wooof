package project.andi.client.material;

import project.andi.client.resources.AndiResources;

import com.google.gwt.user.client.ui.TextBox;

public class AndiTextBox extends TextBox {

	private String placeholder;

	public AndiTextBox() {
		this.addStyleName(AndiResources.INSTANCE.andicss().andiTextBox());
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.getElement().setAttribute("placeholder", placeholder);
	}

}
