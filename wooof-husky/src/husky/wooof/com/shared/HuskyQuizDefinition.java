package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuizDefinition extends HuskyQuizItem{

	private static final long serialVersionUID = 1L;
	private String answer;
	
	public HuskyQuizDefinition() {}


	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
