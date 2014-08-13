package husky.wooof.com.client.dialog;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.ui.HuskyTextBox;
import husky.wooof.com.client.ui.LessonType;
import husky.wooof.com.client.ui.YoutubeVideo;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateLesson extends Composite {

	private static CreateLessonUiBinder uiBinder = GWT
			.create(CreateLessonUiBinder.class);

	interface CreateLessonUiBinder extends UiBinder<Widget, CreateLesson> {
	}
	
	@UiField LessonType typeYoutube, typeImage;
	@UiField HTMLPanel chooseTypePanel, lessonFieldPanel, youtubePanel, imagePanel;
	@UiField YoutubeVideo youtubeVideoPanel;
	@UiField HuskyTextBox txtYoutubeUrl;
	
	public CreateLesson(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
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
	
	
	
	@UiHandler("btnYoutubePreview")
	void onPreviewYoutubeVideo(ClickEvent e){
		youtubeVideoPanel.clear();
		youtubeVideoPanel.setUrl(txtYoutubeUrl.getText());
	}
	
	
}
