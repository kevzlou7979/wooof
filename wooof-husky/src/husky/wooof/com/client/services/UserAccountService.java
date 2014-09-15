package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

@RemoteServiceRelativePath("account")
public interface UserAccountService extends RemoteService {

	public static class Connect {

		private static UserAccountServiceAsync service;

		public static UserAccountServiceAsync getService() {
			if (service == null) {
				service = (UserAccountServiceAsync) GWT.create(UserAccountService.class);
				PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "account");
			}

			return service;
		}
	}

	public HuskyUser register(String firstName, String lastName, String email, String password, String gender) throws Exception;

	public HuskyUser login(String email, String password) throws Exception;

	public HuskyUser getUser(HuskyUser user) throws Exception;

	public HuskyUser getUserById(Long userId) throws Exception;

	public void deleteUser(HuskyUser user) throws Exception;

	public HuskyUser updateUser(HuskyUser user) throws Exception;

	public List<HuskyUser> searchUsers(String filter, HuskyCard card) throws Exception;

}
