package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyLinkLesson extends HuskyLesson {

	private static final long serialVersionUID = 1L;
	private String link;

	public HuskyLinkLesson() {
	}

	public HuskyLinkLesson(Long cardId, String name, String type, String description, String material, String link) {
		super(cardId, name, type, description, material);
		this.setLink(link);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


}
