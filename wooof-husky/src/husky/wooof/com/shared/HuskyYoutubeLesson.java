package husky.wooof.com.shared;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyYoutubeLesson extends HuskyLesson{

	private static final long serialVersionUID = 1L;
	private String youtubeUrl;
	
	public HuskyYoutubeLesson() {}
	
	public HuskyYoutubeLesson(Long cardId, String name, String type, String description, String youtubeUrl) {
		super(cardId, name, type, description);
		this.youtubeUrl = youtubeUrl;
	}
	
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
}
