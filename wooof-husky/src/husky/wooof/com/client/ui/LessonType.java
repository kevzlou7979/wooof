package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LessonType extends Composite {

	private static LessonTypeUiBinder uiBinder = GWT
			.create(LessonTypeUiBinder.class);

	interface LessonTypeUiBinder extends UiBinder<Widget, LessonType> {
	}
	
	@UiField Image imgTitle;
	@UiField Label lblDescription;
	@UiField FocusPanel focusPanel;
	
	private ImageResource resource;
	private String text;
	private String type;
	
	public LessonType() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		imgTitle.setResource(resource);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		lblDescription.setText(text);
		
	}

	public FocusPanel getFocusPanel() {
		return focusPanel;
	}

	public void setFocusPanel(FocusPanel focusPanel) {
		this.focusPanel = focusPanel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	


}
