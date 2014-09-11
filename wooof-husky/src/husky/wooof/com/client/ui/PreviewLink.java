package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class PreviewLink extends SimplePanel {

	private String linkUrl;
	private Image image = new Image();

	public PreviewLink() {
		this.setWidth("100%");
		this.setHeight("100%");
		image.setResource(HuskyResources.INSTANCE.ic_preview_link());
		image.getElement().getStyle().setMarginTop(50, Unit.PX);
		this.add(image);
	}

	private void loadLink(String linkUrl) {
		this.clear();
		Frame frame = new Frame();
		frame.setWidth("100%");
		frame.setHeight("100%");
		frame.setUrl(linkUrl);
		this.setWidget(frame);
	}

	public String getLink() {
		return linkUrl;
	}

	public void setLink(String linkUrl) {
		this.linkUrl = linkUrl;
		loadLink(linkUrl);
	}
}
