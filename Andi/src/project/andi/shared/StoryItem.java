package project.andi.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class StoryItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String title;
	private String introduction;
	private String imageUrl;
	private String content;
	private String place;
	private Long storyId;
	private Date creationDate = new Date();
	
	public StoryItem() {
		// TODO Auto-generated constructor stub
	}

	

	public StoryItem(String title, String introduction, String imageUrl,
			String content, String place,Date creationDate, Long storyId) {
		super();
		this.title = title;
		this.introduction = introduction;
		this.imageUrl = imageUrl;
		this.content = content;
		this.place = place;
		this.creationDate = creationDate;
		this.storyId = storyId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}


	
}
