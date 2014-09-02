package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class UploadedImage implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SERVING_URL = "servingUrl";
	public static final String CREATED_AT = "createdAt";
	public static final String OWNER_ID = "ownerId";

	@Id
	String key;
	String servingUrl;
	Date createdAt;
	String ownerId; // Refers to the User that uploaded this

	public UploadedImage() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getServingUrl() {
		return servingUrl;
	}

	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
