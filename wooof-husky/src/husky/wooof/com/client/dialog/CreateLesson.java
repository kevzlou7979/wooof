package husky.wooof.com.client.dialog;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.LessonService;
import husky.wooof.com.client.ui.HuskyMessage;
import husky.wooof.com.client.ui.HuskyTextArea;
import husky.wooof.com.client.ui.HuskyTextBox;
import husky.wooof.com.client.ui.HuskyUploadArea;
import husky.wooof.com.client.ui.LessonType;
import husky.wooof.com.client.ui.YoutubeVideo;
import husky.wooof.com.shared.FieldVerifier;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyYoutubeLesson;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateLesson extends Composite {

	private static CreateLessonUiBinder uiBinder = GWT
			.create(CreateLessonUiBinder.class);

	interface CreateLessonUiBinder extends UiBinder<Widget, CreateLesson> {
	}
	
	private String type;
	@UiField LessonType typeYoutube, typeImage;
	@UiField HTMLPanel chooseTypePanel, lessonFieldPanel, youtubePanel, imagePanel, messagePanel, panel;
	@UiField YoutubeVideo youtubeVideoPanel;
	@UiField HuskyUploadArea imageLessonPanel;
	@UiField HuskyTextBox txtYoutubeUrl, txtLessonName;
	@UiField HuskyTextArea txtDescription;
	
	private HuskyMain huskyMain;
	private WorkspaceMain workspaceMain;
	
	public CreateLesson(HuskyMain huskyMain, WorkspaceMain workspaceMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		this.setWorkspaceMain(workspaceMain);
		registerEvents();
		onSelectLessonType(typeYoutube);
	}
	
	private void registerEvents(){
		
		typeYoutube.getFocusPanel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				onSelectLessonType(typeYoutube);
			}
		});
		typeImage.getFocusPanel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				onSelectLessonType(typeImage);
			}
		});
	}
	
	
	
	private void onSelectLessonType(LessonType lessonType){
		for(Widget w : chooseTypePanel){
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		}
		lessonType.addStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		lessonFieldPanel.clear();
		this.type = lessonType.getType();
		switch (lessonType.getType()) {
		case IHuskyConstants.LESSON_YOUTUBE:
			lessonFieldPanel.add(youtubePanel);
			break;
		case IHuskyConstants.LESSON_IMAGE:
			lessonFieldPanel.add(imagePanel);
			break;
		default:
			break;
		}
		
	}
	
	private void saveLesson(HuskyLesson lesson){
		LessonService.Connect.getService().saveLesson(lesson, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				huskyMain.getHuskyDialog().hide();
				workspaceMain.getAllCardLessons();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				HuskyMessage.showMessage(false, messagePanel, caught.getMessage());
			}
		});
	}
	
	
	@UiHandler("btnCreateLesson")
	void onCreateLesson(ClickEvent e){
		if(type.equals(IHuskyConstants.LESSON_YOUTUBE) && (FieldVerifier.isValidFields(panel, messagePanel) && FieldVerifier.isValidFields(youtubePanel, messagePanel))){
			saveLesson(new HuskyYoutubeLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), txtYoutubeUrl.getText()));
		}else if(type.equals(IHuskyConstants.LESSON_IMAGE) && (FieldVerifier.isValidFields(panel, messagePanel))){
			saveLesson(new HuskyImageLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), imageLessonPanel.getCardImage().getUrl()));
		}
	}
	
	@UiHandler("btnYoutubePreview")
	void onPreviewYoutubeVideo(ClickEvent e){
		youtubeVideoPanel.clear();
		youtubeVideoPanel.setUrl(txtYoutubeUrl.getText());
	}

	public WorkspaceMain getWorkspaceMain() {
		return workspaceMain;
	}

	public void setWorkspaceMain(WorkspaceMain workspaceMain) {
		this.workspaceMain = workspaceMain;
	}
	
	
}
