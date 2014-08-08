package husky.wooof.com.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String name;
	private String description;
	private Date creationDate = new Date();
	private List<Key<HuskyUser>> admins = new ArrayList<Key<HuskyUser>>();
	private List<Key<HuskyUser>> viewers = new ArrayList<Key<HuskyUser>>();
	private String cardImage;
	
	public HuskyCard() {}
	
	public HuskyCard(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardImage() {
		return cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}

	public List<Key<HuskyUser>> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Key<HuskyUser>> admins) {
		this.admins = admins;
	}

	public List<Key<HuskyUser>> getViewers() {
		return viewers;
	}

	public void setViewers(List<Key<HuskyUser>> viewers) {
		this.viewers = viewers;
	}

}
