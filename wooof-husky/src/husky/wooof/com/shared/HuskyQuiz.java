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

	public HuskyQuiz() {
	}

	public HuskyQuiz(Long cardId, String title, String description,
			double totalPoints, Date creationDate) {
		super();
		this.title = title;
		this.description = description;
		this.totalPoints = totalPoints;
		this.name = title;
		this.cardId = cardId;
		this.type = "Quiz";
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

}
