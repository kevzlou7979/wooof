package drivepicker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import drivepicker.shared.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
	public static class Connect {

		private static GreetingServiceAsync service;

		public static GreetingServiceAsync getService() {
			if (service == null) {
				service = (GreetingServiceAsync) GWT.create(GreetingService.class);
				//PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "Greeting");
			}

			return service;
		}
	}

	String greetServer(String name) throws IllegalArgumentException;
	User login(String token) throws Exception;
}
