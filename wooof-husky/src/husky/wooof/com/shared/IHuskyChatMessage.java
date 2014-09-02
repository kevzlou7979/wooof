package husky.wooof.com.shared;

import java.util.Date;

public interface IHuskyChatMessage {

	void setCreationDate(Date creationDate);

	Date getCreationDate();

	String getMessage();

	void setMessage(String message);

	String getUser();

	void setUser(String user);

	String getProfilePic();

	void setProfilePic(String profilePic);

	Long getUserId();

	void setUserId(Long userId);
}
