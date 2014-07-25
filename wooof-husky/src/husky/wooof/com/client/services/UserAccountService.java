package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("account")
public interface UserAccountService extends RemoteService {
	
	public static class Connect {

		private static UserAccountServiceAsync service;

		public static UserAccountServiceAsync getService() {
			if (service == null) {
				service = (UserAccountServiceAsync) GWT.create(UserAccountService.class);
			}

			return service;
		}
	}
	
	public HuskyUser register(String firstName, String lastName, String email, String password) throws Exception;
	
	public HuskyUser login(String email, String password) throws Exception;
	
	public HuskyUser getUser(HuskyUser user) throws Exception;
	
	public void deleteUser(HuskyUser user) throws Exception;
	
	public HuskyUser updateUser(HuskyUser user) throws Exception;
	
}

