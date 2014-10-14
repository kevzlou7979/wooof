package drivepicker.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import drivepicker.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void login(String token, AsyncCallback<User> callback);
}
