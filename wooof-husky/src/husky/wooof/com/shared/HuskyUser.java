package husky.wooof.com.shared;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String password = "";
	private String tagline = "";
	private String introduction = "";
	private String gender = "";
	private String profilePic = "";
	private String workspacePic = "";

	public HuskyUser() {
	}

	public HuskyUser(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public HuskyUser(String firstName, String lastName, String email, String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public HuskyUser(String firstName, String lastName, String email, String password, String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWorkspacePic() {
		return workspacePic;
	}

	public void setWorkspacePic(String workspacePic) {
		this.workspacePic = workspacePic;
	}
}
