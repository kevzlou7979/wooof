package project.andi.client.material;

import project.andi.client.page.MainPage;
import project.andi.shared.Story;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SideBar extends Composite  {

	private static SideBarUiBinder uiBinder = GWT.create(SideBarUiBinder.class);

	interface SideBarUiBinder extends UiBinder<Widget, SideBar> {
	}
	
	@UiField Label lblWoman, lblMan, lblNoViews;
	@UiField Image imgCover;
	@UiField HTMLPanel panel;
	
	private MainPage mainPage;

	public SideBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setUI(Story story) {
		imgCover.setUrl(story.getCoverPhoto());
		lblWoman.setText(story.getWoman());
		lblMan.setText(story.getMan());
		lblNoViews.setText(String.valueOf(story.getViews()));
	}
	

	public MainPage getMainPage() {
		return mainPage;
	}

	public void setMainPage(MainPage mainPage) {
		this.mainPage = mainPage;
	}

	public HTMLPanel getPanel() {
		return panel;
	}

	public void setPanel(HTMLPanel panel) {
		this.panel = panel;
	}

	
}
