package husky.wooof.com.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;

public interface UserAccountServiceAsync {

	public void login(String email, String password, AsyncCallback<HuskyUser> callback);

	public void login(String oauthToken, AsyncCallback<HuskyUser> callback);
	
	public void register(String firstName, String lastName, String email, String password, String gender, AsyncCallback<HuskyUser> callback);

	public void getUser(HuskyUser user, AsyncCallback<HuskyUser> callback);

	public void deleteUser(HuskyUser user, AsyncCallback<Void> callback);

	public void updateUser(HuskyUser user, AsyncCallback<HuskyUser> callback);

	public void searchUsers(String filter, HuskyCard card, AsyncCallback<List<HuskyUser>> callback);

	public void getUserById(Long userId, AsyncCallback<HuskyUser> callback);
}
