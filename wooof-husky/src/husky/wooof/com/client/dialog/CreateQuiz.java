package husky.wooof.com.client.dialog;

import husky.wooof.com.client.ui.HuskyTitle;
import husky.wooof.com.client.ui.QuizItem;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateQuiz extends Composite {

	private static CreateQuizUiBinder uiBinder = GWT
			.create(CreateQuizUiBinder.class);

	interface CreateQuizUiBinder extends UiBinder<Widget, CreateQuiz> {
	}

	@UiField HTMLPanel quizPanel;
	@UiField HuskyTitle menuQuizMultiChoice, menuQuizTrueFalse, menuQuizDef, menuQuizMatching, menuQuizEssay, menuQuizCheckBox;
	private int quizNoItems = 1;
	public CreateQuiz() {
		initWidget(uiBinder.createAndBindUi(this));
		registerMenuHandler();
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

	
	
}
