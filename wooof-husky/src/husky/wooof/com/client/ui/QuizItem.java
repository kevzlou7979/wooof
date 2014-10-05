package husky.wooof.com.client.ui;

import husky.wooof.com.client.dialog.CreateQuiz;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuizItem extends Composite {

	private static QuizItemUiBinder uiBinder = GWT
			.create(QuizItemUiBinder.class);

	interface QuizItemUiBinder extends UiBinder<Widget, QuizItem> {
	}
	
	private String title="Sample Item Question";
	private double points = 0;
	private String name = "mChoice";
	private Label lblTitle = new Label();

	@UiField Label lblItemNo;
	@UiField TextBox txtPoints;
	@UiField HTMLPanel quizPanel, multipleChoicePanel, titlePanel;
	private CreateQuiz createQuiz;
	
	public QuizItem(HuskyQuiz quiz, int itemNo, CreateQuiz createQuiz) {
		initWidget(uiBinder.createAndBindUi(this));
		this.createQuiz = createQuiz;
		quiz.setTitle(title);
		quiz.setPoint(points);
		txtPoints.setText(String.valueOf(quiz.getPoint()));
		lblTitle.setText(quiz.getTitle());
		lblItemNo.setText(String.valueOf(itemNo));
		titlePanel.add(lblTitle);
		quizPanel.clear();
		if(quiz instanceof HuskyQuizMultiplechoice){
			quizPanel.add(multipleChoicePanel);
		}
		
		for(Widget w : multipleChoicePanel){
			if(w instanceof ChoiceItem){
				((ChoiceItem) w).setName(name);
			}
		}
	}
	
	@UiHandler("btnEditTitle")
	void onEditTitle(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz title", new HuskyCkeditor(titlePanel),60);
	}
	
	@UiHandler("btnDelete")
	void onDelete(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_WARN, "Are you sure you want to delete this quiz item?", "Oops wait a second...", this, 50);
	}

	public void deleteQuizItem(){
		this.removeFromParent();
		createQuiz.setQuizNoItems(createQuiz.getQuizNoItems() - 1);
	}
	
}
