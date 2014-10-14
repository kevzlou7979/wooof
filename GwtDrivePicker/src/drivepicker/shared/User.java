package drivepicker.shared;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean loggedIn = false;

	private String loginUrl;

	private String logoutUrl;

	private String emailAddress;

	private String nickname;

	private String pictureUrl;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(boolean loggedIn, String loginUrl, String logoutUrl,
			String emailAddress, String nickname, String pictureUrl) {
		super();
		this.loggedIn = loggedIn;
		this.loginUrl = loginUrl;
		this.logoutUrl = logoutUrl;
		this.emailAddress = emailAddress;
		this.nickname = nickname;
		this.pictureUrl = pictureUrl;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
}
