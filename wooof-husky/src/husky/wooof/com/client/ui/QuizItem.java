package husky.wooof.com.client.ui;

import husky.wooof.com.shared.HuskyQuiz;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
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
	
	@UiField Label lblTitle;
	@UiField TextBox txtPoints;
	
	public QuizItem(HuskyQuiz quiz) {
		initWidget(uiBinder.createAndBindUi(this));
		quiz.setTitle(title);
		quiz.setPoint(points);
		txtPoints.setText(String.valueOf(quiz.getPoint()));
		lblTitle.setText(quiz.getTitle());
	}

}
