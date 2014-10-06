package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizMultiplechoice extends HuskyQuizItem{

	private static final long serialVersionUID = 1L;
	private String items;
	private int correctAnswer;
	
	public HuskyQuizMultiplechoice() {}
	
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
