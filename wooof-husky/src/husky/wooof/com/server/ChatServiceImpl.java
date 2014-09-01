package husky.wooof.com.server;

import java.util.Date;

import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyUserCard;
import husky.wooof.com.shared.IHuskyChatMessage;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;
import husky.wooof.com.shared.MyFactory;
import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.googlecode.objectify.Objectify;

public class ChatServiceImpl extends ChannelServer {

	private static final long serialVersionUID = 8190116019972959249L;

	private Objectify ofy = OfyService.ofy();

	public void onJoin(String token, String channelName) {

	}

	public void onMessage(String token, String channelName, String message) {
		try {
			MyFactory myFactory = AutoBeanFactorySource.create(MyFactory.class);
			IHuskyChatMessage myMessage = myFactory.getMessage().as();
			String[] result = message.split(";");
			HuskyUser user = ofy.query(HuskyUser.class).filter("id", Long.decode(result[0])).get();
			myMessage.setUser(user.getEmail());
			myMessage.setUserId(user.getId());
			myMessage.setMessage(result[1]);
			myMessage.setProfilePic(user.getProfilePic());
			myMessage.setCreationDate(new Date());

			HuskyUserCard userCard = ofy.query(HuskyUserCard.class)
					.filter("userId", user.getId())
					.filter("cardId", Long.parseLong(channelName)).get();
			if (userCard == null) {
				userCard = new HuskyUserCard(Long.parseLong(channelName),user.getId(), false);
				ofy.put(userCard);
			}
			if (result[1].equals(IHuskyConstants.CHAT_JOINED)) {
				ofy.delete(userCard);
				userCard.setActive(true);
				ofy.put(userCard);
			} else if (result[1].equals(IHuskyConstants.CHAT_LEAVE)) {
				ofy.delete(userCard);
				userCard.setActive(false);
				ofy.put(userCard);
			} else {
				ofy.put(new HuskyChatMessage(myMessage.getMessage(), user.getId(), Long.parseLong(channelName), myMessage.getUser(), myMessage.getProfilePic()));
			}
			

			send(channelName, myMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
