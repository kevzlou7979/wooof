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

public class Banner extends Composite {

	private static BannerUiBinder uiBinder = GWT.create(BannerUiBinder.class);

	interface BannerUiBinder extends UiBinder<Widget, Banner> {
	}

	@UiField HTMLPanel soundCloudPanel,panel;
	@UiField Label lblWoman, lblMan, txtNoViews;
	@UiField Image imgBanner;
	
	private MainPage mainPage;
	
	public Banner() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Banner(MainPage mainPage) {
		initWidget(uiBinder.createAndBindUi(this));
		this.mainPage = mainPage;
	}

	public void setUI(Story story) {
		imgBanner.setUrl(story.getCoverPhoto());
		lblWoman.setText(story.getWoman());
		lblMan.setText(story.getMan());
		txtNoViews.setText(String.valueOf(story.getViews()));
	}
	

	public MainPage getMainPage() {
		return mainPage;
	}

	public void setMainPage(MainPage mainPage) {
		this.mainPage = mainPage;
	}
	
}
