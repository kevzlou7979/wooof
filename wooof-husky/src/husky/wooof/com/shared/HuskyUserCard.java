package husky.wooof.com.shared;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyUserCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Long cardId;
	private Long userId;
	private Integer numNewChat = 0;
	private boolean active;
	
	public HuskyUserCard() {}

	public HuskyUserCard(Long cardId, Long userId, boolean active) {
		super();
		this.cardId = cardId;
		this.userId = userId;
		this.active = active;
	}
	
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getNumNewChat() {
		return numNewChat;
	}

	public void setNumNewChat(Integer numNewChat) {
		this.numNewChat = numNewChat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
