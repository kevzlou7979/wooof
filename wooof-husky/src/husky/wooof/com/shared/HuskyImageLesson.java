package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyImageLesson extends HuskyLesson {

	private static final long serialVersionUID = 1L;
	private String imageUrl;

	public HuskyImageLesson() {
	}

	public HuskyImageLesson(Long cardId, String name, String type, String description, String imageUrl) {
		super(cardId, name, type, description);
		this.setImageUrl(imageUrl);
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
