package husky.wooof.com.client.main;

import java.util.List;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.LessonService;
import husky.wooof.com.client.ui.LessonItem;
import husky.wooof.com.client.ui.YoutubeVideo;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyYoutubeLesson;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceMain extends Composite{

	private static WorkspaceMainUiBinder uiBinder = GWT
			.create(WorkspaceMainUiBinder.class);

	interface WorkspaceMainUiBinder extends UiBinder<Widget, WorkspaceMain> {
	}

	@UiField HTMLPanel panel, activeUsersPanel, lessonPanel,lessonPreviewPanel;
	@UiField LessonItem createLesson;
	
	private HuskyCardNavigation cardNavigation;
	private HuskyCard card;
	private HuskyMain huskyMain;
	
	public WorkspaceMain(HuskyMain huskyMain, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		this.card = card;
		createLesson.setHuskyMain(huskyMain);
		createLesson.setWorkspaceMain(this);
		huskyMain.minimizeSideBar(true);
		huskyMain.getHuskyMainPanel().setWidth("100%");
		cardNavigation = new HuskyCardNavigation(this);
		cardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().cardNavigation());
		panel.add(cardNavigation);
		getAllCardLessons();
	}

	public HuskyCard getCard() {
		return card;
	}

	public void setCard(HuskyCard card) {
		this.card = card;
	}

	public HuskyMain getHuskyMain() {
		return huskyMain;
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}

	public HTMLPanel getActiveUsersPanel() {
		return activeUsersPanel;
	}

	public void setActiveUsersPanel(HTMLPanel activeUsersPanel) {
		this.activeUsersPanel = activeUsersPanel;
	}
	
	public void getAllCardLessons(){
		lessonPanel.clear();
		lessonPanel.add(createLesson);
		LessonService.Connect.getService().getAllCardLessons(card, new AsyncCallback<List<HuskyLesson>>() {
			
			@Override
			public void onSuccess(List<HuskyLesson> result) {
				int i = 1;
				for(HuskyLesson lesson : result){
					lessonPanel.add(new LessonItem(lesson, i, WorkspaceMain.this));
					i++;
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
	}

	public void playYoutubeLesson(HuskyYoutubeLesson lesson){
		YoutubeVideo video = new YoutubeVideo();
		video.setUrl(lesson.getYoutubeUrl());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(video);
	}
	
	public void playImageLesson(HuskyImageLesson lesson){
		Image img = new Image();
		img.setUrl(lesson.getImageUrl());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(img);
	}
	

}
