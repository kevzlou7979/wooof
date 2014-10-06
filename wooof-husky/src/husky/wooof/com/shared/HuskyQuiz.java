package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyQuiz implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Long cardId;
	private String title;
	private String description;
	private double totalPoints;
	private Date creationDate = new Date();

	public HuskyQuiz() {
	}

	public HuskyQuiz(Long cardId, String title, String description,
			double totalPoints, Date creationDate) {
		super();
		this.cardId = cardId;
		this.title = title;
		this.description = description;
		this.totalPoints = totalPoints;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
