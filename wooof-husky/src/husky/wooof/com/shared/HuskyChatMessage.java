package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyChatMessage implements Serializable, IHuskyChatMessage {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String message;
	private Long objectId;
	private Long cardId;
	private Date creationDate = new Date();

	private String user;
	private String profilePic;

	public HuskyChatMessage() {
	}

	public HuskyChatMessage(String message, Long objectId, Long cardId, String user, String profilePic) {
		super();
		this.message = message;
		this.objectId = objectId;
		this.cardId = cardId;
		this.user = user;
		this.profilePic = profilePic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

}
