package husky.wooof.com.server;

import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.shared.HuskyUser;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;


public class UserAccountServiceImpl extends RemoteServiceServlet implements UserAccountService {

	private static final long serialVersionUID = 1L;
	
	private Objectify ofy = OfyService.ofy();
	
	
	@Override
	public HuskyUser login(String userName, String password) throws Exception {
		return getUser(new HuskyUser(userName, password));
	}


	@Override
	public HuskyUser register(String firstName, String lastName, String email, String password, String gender)
			throws Exception {
		HuskyUser user = new HuskyUser(firstName, lastName, email, MD5Helper.encode(password), gender);
		ofy.put(user);
		return user;
	}


	@Override
	public HuskyUser getUser(HuskyUser user) throws Exception {
		return ofy.query(HuskyUser.class).filter("email", user.getEmail()).filter("password", MD5Helper.encode(user.getPassword())).get();  
	}
	
	@Override
	public HuskyUser getUserById(Long userId) throws Exception {
		return ofy.query(HuskyUser.class).filter("id", userId).get();
	}
	
	@Override
	public void deleteUser(HuskyUser user) throws Exception {
		ofy.delete(user);
	}


	@Override
	public HuskyUser updateUser(HuskyUser user) throws Exception {
		ofy.put(user);
		return getUser(user);
	}


	@Override
	public List<HuskyUser> searchUsers(String filter) throws Exception {
		List<HuskyUser> users = new ArrayList<HuskyUser>();
		if(!filter.isEmpty()){
			for(HuskyUser user : ofy.query(HuskyUser.class).filter("email >=", filter).filter("email <", filter+"Z")){
				users.add(user);
			}
		}
		
		return users;
	}
}