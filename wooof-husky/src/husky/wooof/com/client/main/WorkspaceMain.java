package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.services.LessonService;
import husky.wooof.com.client.ui.GoogleDocViewer;
import husky.wooof.com.client.ui.LessonItem;
import husky.wooof.com.client.ui.YoutubeVideo;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyYoutubeLesson;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceMain extends Composite {

	private static WorkspaceMainUiBinder uiBinder = GWT.create(WorkspaceMainUiBinder.class);

	interface WorkspaceMainUiBinder extends UiBinder<Widget, WorkspaceMain> {
	}

	@UiField
	HTMLPanel panel, activeUsersPanel, lessonPanel, lessonPreviewPanel, materialPanel;
	@UiField
	LessonItem createLesson;
	@UiField 
	Label lblLessonTitle, lblLessonDescription, lblBrowseMaterial;

	private HuskyCardNavigation cardNavigation;
	private HuskyCard card;
	private HuskyMain huskyMain;

	public WorkspaceMain(HuskyMain huskyMain, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		lblBrowseMaterial.setVisible(false);
		this.huskyMain = huskyMain;
		this.card = card;
		createLesson.setHuskyMain(huskyMain);
		createLesson.setWorkspaceMain(this);
		huskyMain.minimizeSideBar(true);
		huskyMain.getHuskyMainPanel().setWidth("100%");
		cardNavigation = new HuskyCardNavigation(this);
		cardNavigation.addStyleName(HuskyResources.INSTANCE.huskycss().cardNavigation() + " " + HuskyResources.INSTANCE.huskymobilecss().cardNavigation());
		panel.add(cardNavigation);
		materialPanel.setVisible(false);
		getAllCardLessons();
		onJoinCard();
	}
	
	private void onJoinCard(){
		CardService.Connect.getService().onJoinCard(huskyMain.getUser(), card, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
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

	public void getAllCardLessons() {
		lessonPanel.clear();
		lessonPanel.add(createLesson);
		LessonService.Connect.getService().getAllCardLessons(card, new AsyncCallback<List<HuskyLesson>>() {

			@Override
			public void onSuccess(List<HuskyLesson> result) {
				int i = 1;
				for (HuskyLesson lesson : result) {
					LessonItem item = new LessonItem(lesson, i, WorkspaceMain.this);
					lessonPanel.add(item);
					if(i == 1){
						executeLesson(item);
					}
					i++;
				}
				
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});

	}
	
	public void executeLesson(LessonItem  item){
		HuskyLesson lesson = item.getLesson();
		for(Widget w : lessonPanel){
			w.getElement().getStyle().setOpacity(0.3);
		}
		lblLessonTitle.setText(lesson.getName());
		lblLessonDescription.setText(lesson.getDescription());
		onResetMaterialPanel();
		if(lesson.getMaterialUrl() == null){
			lblBrowseMaterial.setVisible(false);
		}else{
			lblBrowseMaterial.setVisible(true);
			lblBrowseMaterial.setLayoutData(lesson);
		}
		
		item.getElement().getStyle().setOpacity(1);
		switch (lesson.getType()) {
		case IHuskyConstants.LESSON_YOUTUBE:
			playYoutubeLesson((HuskyYoutubeLesson)lesson);
			break;
		case IHuskyConstants.LESSON_IMAGE:
			playImageLesson((HuskyImageLesson)lesson);
			break;

		default:
			break;
		}
	}

	public void playYoutubeLesson(HuskyYoutubeLesson lesson) {
		YoutubeVideo video = new YoutubeVideo();
		video.setUrl(lesson.getYoutubeUrl());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(video);
	}

	public void playImageLesson(HuskyImageLesson lesson) {
		Image img = new Image();
		img.setWidth("100%");
		img.setHeight("100%");
		img.setUrl(lesson.getImageUrl());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(img);
	}
	
	@UiHandler("lblBrowseMaterial")
	void onBrowseMaterial(ClickEvent e){
		if(!materialPanel.isVisible()){
			materialPanel.clear();
			materialPanel.setVisible(true);
			GoogleDocViewer viewer = new GoogleDocViewer(((HuskyLesson)lblBrowseMaterial.getLayoutData()).getMaterialUrl());
			materialPanel.add(viewer);
			lessonPreviewPanel.addStyleName(HuskyResources.INSTANCE.huskycss().huskyMaterialMode());
			lblBrowseMaterial.setText("Return to Normal Mode");
		}else{
			onResetMaterialPanel();
		}
	}
	
	private void onResetMaterialPanel(){
		lblBrowseMaterial.setText("Browse Material");
		materialPanel.clear();
		materialPanel.setVisible(false);
		lessonPreviewPanel.removeStyleName(HuskyResources.INSTANCE.huskycss().huskyMaterialMode());
	}

}
