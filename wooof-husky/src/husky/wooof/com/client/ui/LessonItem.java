package husky.wooof.com.client.ui;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.dialog.CreateLesson;
import husky.wooof.com.client.dialog.HuskyDialog;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyYoutubeLesson;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LessonItem extends Composite {

	private static LessonItemUiBinder uiBinder = GWT.create(LessonItemUiBinder.class);

	interface LessonItemUiBinder extends UiBinder<Widget, LessonItem> {
	}

	private HuskyMain huskyMain;
	private String action = "";
	private WorkspaceMain workspaceMain;
	private HuskyLesson lesson;

	@UiField
	FocusPanel itemPanel;
	@UiField
	Label lblLessonNumber, lblLessonName, lblLessonType;

	public LessonItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public LessonItem(HuskyLesson lesson, final int i, WorkspaceMain workspaceMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.lesson = lesson;
		this.workspaceMain = workspaceMain;
		Timer timer = new Timer() {
			@Override
			public void run() {
				itemPanel.addStyleName("panelAfter");
				itemPanel.getElement().setAttribute("style", "transition-delay: " + String.valueOf(i * 50) + "ms");
			}
		};
		timer.schedule(1000);
		lblLessonNumber.setText(String.valueOf(i));
		lblLessonName.setText(lesson.getName());
		lblLessonType.setText(lesson.getType());
		lblLessonNumber.addStyleName(HuskyResources.INSTANCE.huskycss().lessonItem());

	}

	@UiHandler("itemPanel")
	void onClickItem(ClickEvent e) {
		if (action != null && action.equals(IHuskyConstants.ACTION_CREATE)) {
			HuskyDialog dialog = new HuskyDialog(new CreateLesson(huskyMain, workspaceMain));
			dialog.setWidth("500px");
			dialog.setModal(true);
			dialog.setGlassEnabled(true);
			dialog.center();
			dialog.show();
			huskyMain.setHuskyDialog(dialog);
		}
		else {
			if (lesson instanceof HuskyYoutubeLesson) {
				workspaceMain.playYoutubeLesson((HuskyYoutubeLesson) lesson);
			}
			else if (lesson instanceof HuskyImageLesson) {
				workspaceMain.playImageLesson((HuskyImageLesson) lesson);
			}
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

	public WorkspaceMain getWorkspaceMain() {
		return workspaceMain;
	}

	public void setWorkspaceMain(WorkspaceMain workspaceMain) {
		this.workspaceMain = workspaceMain;
	}

}
