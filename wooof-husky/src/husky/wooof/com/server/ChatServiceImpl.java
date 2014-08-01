package husky.wooof.com.server;

import husky.wooof.com.shared.MyFactory;
import husky.wooof.com.shared.MyMessage;
import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

public class ChatServiceImpl extends ChannelServer {

	private static final long serialVersionUID = 8190116019972959249L;

	public void onJoin(String token, String channelName) {
        System.out.println("Join: " + token + " - " + channelName);
    }

    public void onMessage(String token, String channelName, String message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);

        MyFactory myFactory = AutoBeanFactorySource.create(MyFactory.class);
        MyMessage myMessage = myFactory.getMessage().as();
        myMessage.setMessage(message);
        send(channelName, myMessage);
    }

}
