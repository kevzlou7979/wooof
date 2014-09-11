package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyPlaceLesson extends HuskyLesson {

	private static final long serialVersionUID = 1L;
	private String place;

	public HuskyPlaceLesson() {
	}

	public HuskyPlaceLesson(Long cardId, String name, String type, String description, String material, String place) {
		super(cardId, name, type, description, material);
		this.setPlace(place);
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}


}
