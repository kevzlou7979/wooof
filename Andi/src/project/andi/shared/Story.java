package project.andi.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Story implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String code;
	private String password;
	private String title;
	private String description;
	private Date creationDate = new Date();
	private String coverPhoto = "http://1.bp.blogspot.com/-xBx0ZoHPlsE/U-dmZWz0UDI/AAAAAAAADEA/ZtKtwHlZ9nA/s1600/14%2B-%2B1.png";
	private String man = "Boy's Name";
	private String manPhoto;
	private String woman = "Girl's Name";
	private String womanPhoto;
	private long views = 0;

	public Story() {
		// TODO Auto-generated constructor stub
	}

	public Story(String coverPhoto, String man, String manPhoto, String woman,
			String womanPhoto) {
		super();
		this.coverPhoto = coverPhoto;
		this.man = man;
		this.manPhoto = manPhoto;
		this.woman = woman;
		this.womanPhoto = womanPhoto;
	}

	public Story(String code, String password, String title, String description) {
		super();
		this.code = code;
		this.password = password;
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getManPhoto() {
		return manPhoto;
	}

	public void setManPhoto(String manPhoto) {
		this.manPhoto = manPhoto;
	}

	public String getWoman() {
		return woman;
	}

	public void setWoman(String woman) {
		this.woman = woman;
	}

	public String getWomanPhoto() {
		return womanPhoto;
	}

	public void setWomanPhoto(String womanPhoto) {
		this.womanPhoto = womanPhoto;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	
}
