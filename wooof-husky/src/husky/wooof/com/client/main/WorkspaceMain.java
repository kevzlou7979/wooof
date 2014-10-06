package husky.wooof.com.client.main;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.ui.GoogleDocViewer;
import husky.wooof.com.client.ui.LessonItem;
import husky.wooof.com.client.ui.PreviewGoogleMap;
import husky.wooof.com.client.ui.PreviewLink;
import husky.wooof.com.client.ui.PreviewYoutube;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyItem;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyLinkLesson;
import husky.wooof.com.shared.HuskyPlaceLesson;
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
		
		/*LessonService.Connect.getService().getAllCardLessons(card, new AsyncCallback<List<HuskyLesson>>() {

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
		});*/
		
		
		CardService.Connect.getService().getAllItems(card, new AsyncCallback<List<HuskyItem>>() {
			
			@Override
			public void onSuccess(List<HuskyItem> result) {
				int i = 1;
				for (HuskyItem item : result) {
					LessonItem lessonItem = new LessonItem((HuskyLesson) item, i, WorkspaceMain.this);
					lessonPanel.add(lessonItem);
					if(i == 1){
						executeLesson(lessonItem);
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
		HuskyItem huskyItem = item.getItem();
		for(Widget w : lessonPanel){
			w.getElement().getStyle().setOpacity(0.3);
		}
		
		if(huskyItem instanceof HuskyLesson){
			HuskyLesson lesson = (HuskyLesson) huskyItem;
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
			case IHuskyConstants.LESSON_PLACE:
				playPlaceLesson((HuskyPlaceLesson)lesson);
				break;
			case IHuskyConstants.LESSON_AUDIO:
				playAudioLesson((HuskyPlaceLesson)lesson);
				break;
			case IHuskyConstants.LESSON_LINK:
				playLinkLesson((HuskyLinkLesson)lesson);
				break;
			default:
				break;
			}
		}
		
	}

	private void playLinkLesson(HuskyLinkLesson lesson) {
		PreviewLink link = new PreviewLink();
		link.setLink(lesson.getLink());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(link);
	}

	private void playAudioLesson(HuskyPlaceLesson lesson) {
		// TODO Auto-generated method stub
		
	}

	private void playPlaceLesson(HuskyPlaceLesson lesson) {
		PreviewGoogleMap map = new PreviewGoogleMap();
		map.setMap(lesson.getPlace());
		lessonPreviewPanel.clear();
		lessonPreviewPanel.add(map);
	}

	public void playYoutubeLesson(HuskyYoutubeLesson lesson) {
		PreviewYoutube video = new PreviewYoutube();
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
