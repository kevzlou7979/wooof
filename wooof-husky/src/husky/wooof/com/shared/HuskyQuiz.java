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
	private String type;
	private String explanation;
	private double point;
	private Date creationDate = new Date();

	public HuskyQuiz() {
	}

	public HuskyQuiz(Long cardId, String title, String type,
			String explanation, double point, Date creationDate) {
		super();
		this.cardId = cardId;
		this.title = title;
		this.type = type;
		this.explanation = explanation;
		this.point = point;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

}
