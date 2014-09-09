package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GoogleDocViewer extends Composite {

	private static GoogleDocViewerUiBinder uiBinder = GWT.create(GoogleDocViewerUiBinder.class);

	interface GoogleDocViewerUiBinder extends UiBinder<Widget, GoogleDocViewer> {
	}
	
	@UiField HTMLPanel materialPanel;
	
	Frame frame = new Frame();
	public GoogleDocViewer(String url) {
		initWidget(uiBinder.createAndBindUi(this));
		frame.setWidth("100%");
		frame.setHeight("100%");
		materialPanel.clear();
		frame.setUrl("http://docs.google.com/viewer?url=" + url);
		materialPanel.add(frame);
	}

}
