package husky.wooof.com.server;

import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyItem;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyChatMessage;
import husky.wooof.com.shared.IHuskyConstants;
import husky.wooof.com.shared.MyFactory;

import java.util.Date;

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
			
			switch (result[1]) {
			case IHuskyConstants.REAL_BROWSE:
				HuskyItem item = ofy.query(HuskyItem.class).filter("id", Long.decode(result[0])).get();
				myMessage.setMessage(IHuskyConstants.REAL_BROWSE);
				myMessage.setObjectId(item.getId());
				break;
			default:
				HuskyUser user = ofy.query(HuskyUser.class).filter("id", Long.decode(result[0])).get();
				myMessage.setUser(user.getEmail());
				myMessage.setObjectId(user.getId());
				myMessage.setMessage(result[1]);
				myMessage.setProfilePic(user.getProfilePic());
				myMessage.setCreationDate(new Date());
				
				if(!(myMessage.getMessage().equals(IHuskyConstants.CHAT_LEAVE) || myMessage.getMessage().equals(IHuskyConstants.CHAT_JOINED))){
					ofy.put(new HuskyChatMessage(myMessage.getMessage(), user.getId(), Long.parseLong(channelName), myMessage.getUser(), myMessage.getProfilePic()));
				}
				break;
			}
			send(channelName, myMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
