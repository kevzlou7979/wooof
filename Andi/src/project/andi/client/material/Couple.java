package project.andi.client.material;

import project.andi.client.resources.AndiResources;
import project.andi.shared.Story;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Couple extends Composite {

	private static CoupleUiBinder uiBinder = GWT.create(CoupleUiBinder.class);

	interface CoupleUiBinder extends UiBinder<Widget, Couple> {
	}

	@UiField
	HTMLPanel wrap, imageOne, imageTwo, panel;

	private String image;

	public Couple(Story story, final double i) {
		initWidget(uiBinder.createAndBindUi(this));
		Timer timer = new Timer() {
			@Override
			public void run() {
				panel.addStyleName(AndiResources.INSTANCE.andicss().panelAfterCard());
				panel.getElement().getStyle().setOpacity(1);
				panel.getElement().setAttribute("style", "transition-delay: " + String.valueOf(i) + "ms");
			}
		};
		
		setImage("http://www.ilikewallpaper.net/ipad-air-wallpapers/download/4480/Love-Letter-iPad-4-wallpaper-ilikewallpaper_com.jpg");
		setImage(story.getCoverPhoto());
		timer.schedule(500);
	}

	public void setImage(String image) {
		wrap.getElement().setAttribute("style", "background: url(" + image+") center !important; background-size: 250px !important;");
		imageOne.getElement().setAttribute("style", "background: url(" + image+") center !important; background-size: 250px !important;");
		imageTwo.getElement().setAttribute("style", "background: url(" + image+") center !important; background-size: 250px !important;");
	}

	public String getImage() {
		return image;
	}

}
