package husky.wooof.com.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class YoutubeVideo extends SimplePanel {

	private String url;
	private Label label = new Label("No Preview");

	public YoutubeVideo() {
		this.setWidth("100%");
		this.setHeight("100%");
		label.getElement().getStyle().setLineHeight(12, Unit.EM);
		label.getElement().getStyle().setColor("#C6C6C6");
		label.getElement().getStyle().setFontSize(2, Unit.EM);
		this.add(label);
	}

	private void loadYoutubeVideo(String url) {
		this.clear();
		Frame frame = new Frame();
		frame.setWidth("100%");
		frame.setHeight("100%");
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
