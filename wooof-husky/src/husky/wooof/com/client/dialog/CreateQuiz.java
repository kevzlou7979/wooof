package husky.wooof.com.client.dialog;

import husky.wooof.com.client.services.QuizService;
import husky.wooof.com.client.ui.HuskyActionDialog;
import husky.wooof.com.client.ui.HuskyCkeditor;
import husky.wooof.com.client.ui.HuskyTitle;
import husky.wooof.com.client.ui.QuizItem;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizItem;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CreateQuiz extends Composite {

	private static CreateQuizUiBinder uiBinder = GWT
			.create(CreateQuizUiBinder.class);

	interface CreateQuizUiBinder extends UiBinder<Widget, CreateQuiz> {
	}

	@UiField HTMLPanel quizPanel, titlePanel, descriptionPanel;
	@UiField HuskyTitle menuQuizMultiChoice, menuQuizTrueFalse, menuQuizDef, menuQuizMatching, menuQuizEssay, menuQuizCheckBox;
	private int quizNoItems = 1;
	private HuskyCard card;
	private Label lblTitle = new Label("Quiz Title");
	private Label lblDescription = new Label("Quiz Description");
	private List<HuskyQuizItem> items = new ArrayList<HuskyQuizItem>();
	private CreateLesson createLesson;
	
	public CreateQuiz() {
		initWidget(uiBinder.createAndBindUi(this));
		registerMenuHandler();
		titlePanel.add(lblTitle);
		descriptionPanel.add(lblDescription);
	}
	
	private void registerMenuHandler() {
		menuQuizMultiChoice.getPanel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				quizPanel.add(new QuizItem(new HuskyQuizMultiplechoice(), quizNoItems, CreateQuiz.this));
				quizNoItems++;
			}
		});
	}

	public int getQuizNoItems() {
		return quizNoItems;
	}

	public void setQuizNoItems(int quizNoItems) {
		this.quizNoItems = quizNoItems;
	}
	
	@UiHandler("imgEditTitle")
	void onEditTitle(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz title", new HuskyCkeditor(titlePanel ,IHuskyConstants.CK_QUIZ_TITLE, this, true),60);
	}
	
	@UiHandler("imgEditDescription")
	void onEditDescription(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz description", new HuskyCkeditor(descriptionPanel,IHuskyConstants.CK_QUIZ_DESCRIPTION, this, true),60);
	}
	
	@UiHandler("btnSave")
	void onSave(ClickEvent e){
		double totalPoints = 0;
		int totalItems = 0;
		int totalDuration = 0;
		for(Widget w : quizPanel){
			if(w instanceof QuizItem){
				items.add(((QuizItem) w).getQuizItem());
				((QuizItem) w).setChoices(((QuizItem) w).getQuizItem().getItemNo());
				totalPoints = totalPoints + ((QuizItem) w).getQuizItem().getPoint();
				totalItems++;
			}
		}
		
		QuizService.Connect.getService().saveQuiz(new HuskyQuiz(card.getId(), lblTitle.getText(), lblDescription.getText(), totalPoints, new Date(), totalItems, totalDuration, createLesson.getWorkspaceMain().getHuskyMain().getUser().getId()), items, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				createLesson.onReloadWorspace();
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

	public Label getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(Label lblTitle) {
		this.lblTitle = lblTitle;
	}

	public Label getLblDescription() {
		return lblDescription;
	}

	public void setLblDescription(Label lblDescription) {
		this.lblDescription = lblDescription;
	}

	public CreateLesson getCreateLesson() {
		return createLesson;
	}

	public void setCreateLesson(CreateLesson createLesson) {
		this.createLesson = createLesson;
	}


	
}
