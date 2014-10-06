package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizTrueFalse extends HuskyQuizItem{

	private static final long serialVersionUID = 1L;
	private boolean answer;
	
	public HuskyQuizTrueFalse() {}
	
	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
}
