package husky.wooof.com.client.ui;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.dialog.CreateLesson;
import husky.wooof.com.client.dialog.HuskyDialog;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LessonItem extends Composite{

	private static LessonItemUiBinder uiBinder = GWT
			.create(LessonItemUiBinder.class);

	interface LessonItemUiBinder extends UiBinder<Widget, LessonItem> {
	}

	
	private HuskyMain huskyMain;
	private String action;
	
	public LessonItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("itemPanel")
	void onClickItem(ClickEvent e){
		if(action.equals(IHuskyConstants.ACTION_CREATE)){
			HuskyDialog dialog = new HuskyDialog(new CreateLesson(huskyMain));
			dialog.setWidth("500px");
			dialog.setModal(true);
			dialog.setGlassEnabled(true);
			dialog.center();
			dialog.show();
			huskyMain.setHuskyDialog(dialog);
		}
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public HuskyMain getHuskyMain() {
		return huskyMain;
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}
	
}
