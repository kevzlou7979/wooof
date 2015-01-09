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

	public Story() {
		// TODO Auto-generated constructor stub
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

}
