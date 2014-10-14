package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class PreviewGoogleMap extends SimplePanel {

	private String place;
	private Image image = new Image();

	public PreviewGoogleMap() {
		this.setWidth("100%");
		this.setHeight("100%");
		image.setResource(HuskyResources.INSTANCE.ic_preview_map());
		image.getElement().getStyle().setMarginTop(50, Unit.PX);
		this.add(image);
	}

	private void loadMap(String place) {
		this.clear();
		Frame frame = new Frame();
		frame.setWidth("100%");
		frame.setHeight("100%");
		frame.setUrl("https://www.google.com/maps/embed/v1/place?key=" + IHuskyConstants.API_KEY +" &q=" + place.replace(" ", "+"));
		this.setWidget(frame);
	}

	public String getMap() {
		return place;
	}

	public void setMap(String place) {
		this.place = place;
		loadMap(place);
	}
}
