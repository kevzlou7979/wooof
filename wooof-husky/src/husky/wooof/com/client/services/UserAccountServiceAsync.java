package husky.wooof.com.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import husky.wooof.com.shared.HuskyUser;

public interface UserAccountServiceAsync {
	
	public void login(String email, String password, AsyncCallback<HuskyUser> callback);
	
	public void register(String firstName, String lastName, String email,String userName, String password, AsyncCallback<HuskyUser> callback);
	
	public void getUser(HuskyUser user, AsyncCallback<HuskyUser > callback);
	
	public void deleteUser(HuskyUser user, AsyncCallback<HuskyUser > callback);
	
	public void updateUser(HuskyUser user, AsyncCallback<HuskyUser> callback);
}
