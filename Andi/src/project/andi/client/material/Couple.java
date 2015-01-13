package project.andi.client.material;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Couple extends Composite {

	private static CoupleUiBinder uiBinder = GWT.create(CoupleUiBinder.class);

	interface CoupleUiBinder extends UiBinder<Widget, Couple> {
	}

	@UiField
	HTMLPanel wrap, imageOne, imageTwo;

	private String image;

	public Couple() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setImage(String image) {
		wrap.getElement().setAttribute("style", "background: url(" + image+") 0px 0px 220px 220px");
		imageOne.getElement().setAttribute("style", "background: url(" + image+") 0px 0px 220px 220px");
		imageTwo.getElement().setAttribute("style", "background: url(" + image+") 0px 0px 220px 220px");
	}

	public String getImage() {
		return image;
	}

}
