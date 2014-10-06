package husky.wooof.com.shared;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class HuskyLesson  extends HuskyItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	private String materialUrl;

	public HuskyLesson() {
	}

	public HuskyLesson(Long cardId, String name, String type, String description, String materialUrl) {
		super();
		this.description = description;
		this.materialUrl = materialUrl;
		this.cardId = cardId;
		this.type = type;
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaterialUrl() {
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl;
	}

}
