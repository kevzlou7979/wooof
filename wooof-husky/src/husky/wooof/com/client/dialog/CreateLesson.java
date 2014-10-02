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
import husky.wooof.com.client.ui.PreviewGoogleMap;
import husky.wooof.com.client.ui.PreviewLink;
import husky.wooof.com.client.ui.PreviewYoutube;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyLinkLesson;
import husky.wooof.com.shared.HuskyPlaceLesson;
import husky.wooof.com.shared.HuskyYoutubeLesson;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CreateLesson extends Composite {

	private static CreateLessonUiBinder uiBinder = GWT.create(CreateLessonUiBinder.class);

	interface CreateLessonUiBinder extends UiBinder<Widget, CreateLesson> {
	}

	private String type;
	@UiField
	LessonType typeYoutube, typeImage, typeAudio, typePlace, typeLink;
	@UiField
	HTMLPanel infoPanel, chooseTypePanel,buttonPanel,lessonMaterialPanel, stepPanel, lessonFieldPanel, youtubePanel, imagePanel, audioPanel, placePanel, linkPanel, messagePanel, panel, materialPanel;
	@UiField
	PreviewYoutube youtubeVideoPanel;
	@UiField 
	PreviewGoogleMap googleMapPanel;
	@UiField
	HuskyUploadArea imageLessonPanel;
	@UiField 
	PreviewLink linkPreviewPanel;
	@UiField
	HuskyTextBox txtYoutubeUrl, txtLessonName, txtMaterialLink, txtPlaceName, txtLink;
	@UiField
	HuskyTextArea txtDescription;
	@UiField
	Label lblStep1, lblStep2, lblStep3;
	
	private String material;

	private HuskyMain huskyMain;
	private WorkspaceMain workspaceMain;
	private Frame frame = new Frame();

	public CreateLesson(HuskyMain huskyMain, WorkspaceMain workspaceMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = huskyMain;
		this.setWorkspaceMain(workspaceMain);
		registerEvents();
		onSelectLessonType(typeImage);
		onChangeStep(lblStep1, IHuskyConstants.NAV_LESSON_INFO);
	}

	private void registerEvents() {

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
		typeAudio.getFocusPanel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onSelectLessonType(typeAudio);
			}
		});
		typePlace.getFocusPanel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onSelectLessonType(typePlace);
				
			}
		});
		typeLink.getFocusPanel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onSelectLessonType(typeLink);
				
			}
		});
		
	}

	private void onSelectLessonType(LessonType lessonType) {
		for (Widget w : buttonPanel) {
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
		case IHuskyConstants.LESSON_AUDIO:
			lessonFieldPanel.add(audioPanel);
			break;
		case IHuskyConstants.LESSON_PLACE:
			lessonFieldPanel.add(placePanel);
			break;
		case IHuskyConstants.LESSON_LINK:
			lessonFieldPanel.add(linkPanel);
			break;
		default:
			break;
		}

	}

	private void saveLesson(HuskyLesson lesson) {
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
	
	@UiHandler("lblStep1")
	void onStep1(ClickEvent e){
		onChangeStep(lblStep1, IHuskyConstants.NAV_LESSON_INFO);
	}
	
	@UiHandler("lblStep2")
	void onStep2(ClickEvent e){
		onChooseType(e);
	}
	
	@UiHandler("lblStep3")
	void onStep3(ClickEvent e){
		onAddMaterial(e);
	}
	
	@UiHandler("btnChooseType")
	void onChooseType(ClickEvent e){
		huskyMain.getHuskyDialog().getElement().getStyle().setTop(5, Unit.PCT);
		onChangeStep(lblStep2, IHuskyConstants.NAV_LESSON_TYPE);
	}
	
	@UiHandler("btnAddMaterial")
	void onAddMaterial(ClickEvent e){
		onChangeStep(lblStep3, IHuskyConstants.NAV_LESSON_MATERIAL);
	}
	
	
	
	@UiHandler("btnBackChooseType")
	void onBackChooseType(ClickEvent e){
		huskyMain.getHuskyDialog().hide();
	}
	
	@UiHandler("btnBackAddMaterial")
	void onBackAddMaterial(ClickEvent e){
		onChangeStep(lblStep1, IHuskyConstants.NAV_LESSON_INFO);
	}
	
	@UiHandler("btnBackCreateLesson")
	void onBackCreateLesson(ClickEvent e){
		onChangeStep(lblStep2, IHuskyConstants.NAV_LESSON_TYPE);
	}

	@UiHandler("btnCreateLesson")
	void onCreateLesson(ClickEvent e) {
		if (type.equals(IHuskyConstants.LESSON_YOUTUBE)) {
			saveLesson(new HuskyYoutubeLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), material, txtYoutubeUrl.getText()));
		}else if (type.equals(IHuskyConstants.LESSON_IMAGE)) {
			saveLesson(new HuskyImageLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), material, imageLessonPanel.getCardImage().getUrl()));
		}else if (type.equals(IHuskyConstants.LESSON_PLACE)) {
			saveLesson(new HuskyPlaceLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), material, txtPlaceName.getText()));
		}else if (type.equals(IHuskyConstants.LESSON_LINK)) {
			saveLesson(new HuskyLinkLesson(workspaceMain.getCard().getId(), txtLessonName.getText(), type, txtDescription.getText(), material, txtLink.getText()));
		}
	}

	@UiHandler("btnYoutubePreview")
	void onPreviewYoutubeVideo(ClickEvent e) {
		youtubeVideoPanel.clear();
		youtubeVideoPanel.setUrl(txtYoutubeUrl.getText());
	}
	
	@UiHandler("btnMaterialPreview")
	void onPreviewMaterial(ClickEvent e){
		frame.setWidth("100%");
		frame.setHeight("400px");
		materialPanel.clear();
		material = txtMaterialLink.getText().replaceAll("/", "%2F").replaceAll(":", "%3A") + "&embedded=true";
		frame.setUrl("http://docs.google.com/viewer?url=" + material);
		materialPanel.add(frame);
	}
	
	@UiHandler("btnGoogleMapViewer")
	void onPreviewGoogleMap(ClickEvent e){
		googleMapPanel.clear();
		googleMapPanel.setMap(txtPlaceName.getText());
	}
	
	@UiHandler("btnPreviewLink")
	void onPreviewLink(ClickEvent e){
		linkPreviewPanel.clear();
		linkPreviewPanel.setLink(txtLink.getText());
	}
	
	private void onChangeStep(Label label, int type){
		
		for(Widget w : stepPanel){
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().huskyStepActive());
		}
		
		label.addStyleName(HuskyResources.INSTANCE.huskycss().huskyStepActive());
		
		
		
		infoPanel.setVisible(false);
		chooseTypePanel.setVisible(false);
		lessonMaterialPanel.setVisible(false);
		
		switch (type) {
		case IHuskyConstants.NAV_LESSON_INFO:
			infoPanel.setVisible(true);
			break;
		case IHuskyConstants.NAV_LESSON_TYPE:
			chooseTypePanel.setVisible(true);
			break;
		case IHuskyConstants.NAV_LESSON_MATERIAL:
			lessonMaterialPanel.setVisible(true);
			break;

		default:
			break;
		}
	}

	public WorkspaceMain getWorkspaceMain() {
		return workspaceMain;
	}

	public void setWorkspaceMain(WorkspaceMain workspaceMain) {
		this.workspaceMain = workspaceMain;
	}

}
