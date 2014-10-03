package husky.wooof.com.shared;

import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizTrueFalse extends HuskyQuiz{

	private static final long serialVersionUID = 1L;
	private boolean answer;
	
	public HuskyQuizTrueFalse() {}
	
	public HuskyQuizTrueFalse(Long cardId, String title, String type,
			String explanation, double point, Date creationDate,  boolean answer) {
		super(cardId, title, type, explanation, point, creationDate);
		this.answer = answer;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
}
