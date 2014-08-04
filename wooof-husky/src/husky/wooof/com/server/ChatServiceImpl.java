package husky.wooof.com.server;

import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.MyFactory;
import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.googlecode.objectify.Objectify;

public class ChatServiceImpl extends ChannelServer {

	private static final long serialVersionUID = 8190116019972959249L;

	private Objectify ofy = OfyService.ofy();
	
	public void onJoin(String token, String channelName) {
        System.out.println("Join: " + token + " - " + channelName);
    }

    public void onMessage(String token, String channelName, String message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);

        MyFactory myFactory = AutoBeanFactorySource.create(MyFactory.class);
        HuskyChatMessage myMessage = myFactory.getMessage().as();
        String[] result = message.split(";");
	    HuskyUser user = ofy.query(HuskyUser.class).filter("id", Long.decode(result[0])).get();
	    myMessage.setUser(user.getEmail());
	    myMessage.setMessage(result[1]);
        
        send(channelName, myMessage);
    }

}
