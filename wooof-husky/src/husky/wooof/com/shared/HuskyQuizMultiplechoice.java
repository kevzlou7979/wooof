package husky.wooof.com.shared;

import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizMultiplechoice extends HuskyQuiz{

	private static final long serialVersionUID = 1L;
	private String items;
	private int correctAnswer;
	
	public HuskyQuizMultiplechoice() {}
	
	public HuskyQuizMultiplechoice(Long cardId, String title, String type,
			String explanation, double point, Date creationDate, String items, int correctAnswer) {
		super(cardId, title, type, explanation, point, creationDate);
		this.items = items;
		this.correctAnswer = correctAnswer;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
