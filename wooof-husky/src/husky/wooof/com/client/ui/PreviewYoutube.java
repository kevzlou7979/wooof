package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class PreviewYoutube extends SimplePanel {

	private String url;
	private Image image = new Image();

	public PreviewYoutube() {
		this.setWidth("100%");
		this.setHeight("450px");
		image.setResource(HuskyResources.INSTANCE.ic_preview_youtube());
		image.getElement().getStyle().setMarginTop(50, Unit.PX);
		this.add(image);
	}

	private void loadYoutubeVideo(String url) {
		this.clear();
		Frame frame = new Frame();
		frame.setWidth("100%");
		frame.setHeight("450px");
		frame.setUrl(url.replace("watch?v=", "embed/"));
		this.setWidget(frame);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		loadYoutubeVideo(url);
	}
}
