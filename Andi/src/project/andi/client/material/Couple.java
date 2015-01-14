package project.andi.client.material;

import project.andi.client.page.MaintenancePage;
import project.andi.client.resources.AndiResources;
import project.andi.shared.Story;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Couple extends Composite {

	private static CoupleUiBinder uiBinder = GWT.create(CoupleUiBinder.class);

	interface CoupleUiBinder extends UiBinder<Widget, Couple> {
	}

	@UiField
	HTMLPanel wrap, imageOne, imageTwo, panel;

	@UiField
	Label lblCode, lblTitle;
	
	private String image;
	private MaintenancePage maintenancePage;
	private Story story;

	public Couple(MaintenancePage maintenancePage, Story story, final double i) {
		initWidget(uiBinder.createAndBindUi(this));
		this.maintenancePage = maintenancePage;
		this.story = story;
		Timer timer = new Timer() {
			@Override
			public void run() {
				panel.addStyleName(AndiResources.INSTANCE.andicss().panelAfterCard());
				panel.getElement().getStyle().setOpacity(1);
				panel.getElement().setAttribute("style", "transition-delay: " + String.valueOf(i) + "ms");
			}
		};
		lblCode.setText("@" + story.getCode());
		lblTitle.setText(story.getTitle());
		setImage("http://1.bp.blogspot.com/-xBx0ZoHPlsE/U-dmZWz0UDI/AAAAAAAADEA/ZtKtwHlZ9nA/s1600/14%2B-%2B1.png");
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
	
	@UiHandler("btnViewStory")
	void onViewStory(ClickEvent e){
		maintenancePage.loadStory(story.getCode());
	}

}
