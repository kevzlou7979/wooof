package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyLesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Long cardId;
	private String name;
	private String type;
	private String description;
	private Date creationDate = new Date();

	public HuskyLesson() {
	}

	public HuskyLesson(Long cardId, String name, String type, String description) {
		super();
		this.cardId = cardId;
		this.name = name;
		this.type = type;
		this.description = description;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
