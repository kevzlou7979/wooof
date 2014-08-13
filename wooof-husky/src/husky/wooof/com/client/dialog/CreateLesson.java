package husky.wooof.com.client.dialog;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.ui.LessonType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateLesson extends Composite {

	private static CreateLessonUiBinder uiBinder = GWT
			.create(CreateLessonUiBinder.class);

	interface CreateLessonUiBinder extends UiBinder<Widget, CreateLesson> {
	}
	
	@UiField LessonType typeYoutube, typeImage;
	@UiField HTMLPanel chooseTypePanel;
	
	public CreateLesson(HuskyMain huskyMain) {
		initWidget(uiBinder.createAndBindUi(this));
		registerEvents();
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
	
	private void onSelectLessonType(LessonType type){
		for(Widget w : chooseTypePanel){
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
		}
		type.addStyleName(HuskyResources.INSTANCE.huskycss().navBorderActive());
	}
}
