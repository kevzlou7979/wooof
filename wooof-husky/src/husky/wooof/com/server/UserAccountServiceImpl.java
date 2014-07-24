package husky.wooof.com.server;

import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;


public class UserAccountServiceImpl extends RemoteServiceServlet implements UserAccountService {

	private static final long serialVersionUID = 1L;
	
	Objectify ofy = ObjectifyService.begin();
	
	private void registerOfyService(){
		ObjectifyService.register(HuskyUser.class);
	}
	
	public HuskyUser login(String email, String password) {
		return null;
	}


	@Override
	public HuskyUser register(String firstName, String lastName, String email,String userName, String password)
			throws Exception {
		registerOfyService();
		HuskyUser user = new HuskyUser(firstName, lastName, email, userName, password);
		ofy.put(user);
		return user;
	}


	@Override
	public HuskyUser getUser(HuskyUser user) throws Exception {
		registerOfyService();
		return ofy.get(HuskyUser.class, user.getEmail());  
	}


	@Override
	public HuskyUser deleteUser(HuskyUser user) throws Exception {
		ofy.delete(user);
		return null;
	}


	@Override
	public HuskyUser updateUser(HuskyUser user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}