package husky.wooof.com.shared;

import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizDefinition extends HuskyQuiz{

	private static final long serialVersionUID = 1L;
	private String answer;
	
	public HuskyQuizDefinition() {}
	
	public HuskyQuizDefinition(Long cardId, String title, String type,
			String explanation, double point, Date creationDate,  String answer) {
		super(cardId, title, type, explanation, point, creationDate);
		this.answer = answer;
	}

	public String isAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
