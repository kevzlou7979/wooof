package wooof.huskylabs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import wooof.huskylabs.client.services.UserAccountService;
import wooof.huskylabs.shared.HuskyUser;

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
	public HuskyUser register(String firstName, String lastName, String email, String password, String gender) throws Exception {
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
	public HuskyUser login(String oauthToken) throws Exception {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
				+ oauthToken;

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception e) {
				throw e;
			} finally {
				try {
					br.close();
				} catch (final Exception e) {
					throw e;
				}
			}
		} catch (final Exception e) {
			throw e;
		}

		final HuskyUser user = new HuskyUser();
		try {
			final JsonFactory f = new JsonFactory();
			JsonParser jp;
			jp = f.createJsonParser(r.toString());
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldName = jp.getCurrentName();
				jp.nextToken();
				switch (fieldName) {
				case "picture":
					user.setProfilePic(jp.getText());
					break;
				case "name":
					System.out.println("");
					user.setFirstName(jp.getText());
					break;
				case "email":
					user.setEmail(jp.getText());
					break;

				default:
					break;
				}
			}
			HuskyUser existingUser = ofy.query(HuskyUser.class).filter("email", user.getEmail()).get();
			if(existingUser!=null){
				return existingUser;
			}else{
				ofy.put(user);
				return user;
			}
		} catch (final JsonParseException e) {
			throw e;
		} catch (final IOException e) {
			throw e;
		}
	}


}