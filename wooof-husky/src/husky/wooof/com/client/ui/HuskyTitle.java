package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyTitle extends Composite {

	private static HuskyTitleUiBinder uiBinder = GWT.create(HuskyTitleUiBinder.class);

	interface HuskyTitleUiBinder extends UiBinder<Widget, HuskyTitle> {
	}

	@UiField
	Image imgIcon;
	@UiField
	Label lblTitle, lblDescription;
	@UiField
	HTMLPanel headerPanel;
	@UiField FocusPanel panel;

	private String title;
	private String description;
	private String color;
	private boolean border;
	private String imageSize;
	private String fontSize;
	private ImageResource resource;
	private boolean withImage;
	private Composite composite;

	public HuskyTitle() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		if(resource == null){
			imgIcon.removeFromParent();
		}else{
			imgIcon.setResource(resource);
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		lblTitle.getElement().getStyle().setColor("#" + color);
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
		if (!border) {
			headerPanel.getElement().getStyle().setProperty("border", "none");
		}
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
		imgIcon.setWidth(imageSize);
		imgIcon.setHeight(imageSize);
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		lblTitle.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public boolean isWithImage() {
		return withImage;
	}

	public void setWithImage(boolean withImage) {
		if(!withImage){
			imgIcon.removeFromParent();
		}
		this.withImage = withImage;
	}

	public Composite getComposite() {
		return composite;
	}

	public void setComposite(Composite composite) {
		this.composite = composite;
	}

	public FocusPanel getPanel() {
		return panel;
	}

	public void setPanel(FocusPanel panel) {
		this.panel = panel;
	}

	
}
