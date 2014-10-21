package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyQuiz extends HuskyItem  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private double totalPoints;
	private int totalItems;
	private long quizUserId;
	private int totalDurationSec;

	public HuskyQuiz() {
	}
	
	

	public HuskyQuiz(Long cardId, String name, String type) {
		super(cardId, name, type);
		// TODO Auto-generated constructor stub
	}



	public HuskyQuiz(Long cardId, String title, String description,
			double totalPoints, Date creationDate, int totalItems,int totalDurationSec,long quizUserId) {
		super();
		this.title = title;
		this.description = description;
		this.totalPoints = totalPoints;
		this.name = title;
		this.cardId = cardId;
		this.type = "Quiz";
		this.totalItems = totalItems;
		this.totalDurationSec = totalDurationSec;
		this.quizUserId = quizUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalDurationSec() {
		return totalDurationSec;
	}

	public void setTotalDurationSec(int totalDurationSec) {
		this.totalDurationSec = totalDurationSec;
	}



	public long getQuizUserId() {
		return quizUserId;
	}



	public void setQuizUserId(long quizUserId) {
		this.quizUserId = quizUserId;
	}

}
