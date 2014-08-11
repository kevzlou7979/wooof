package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.ui.ChatMessageItem;
import husky.wooof.com.client.ui.HuskyTextArea;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.IHuskyConstants;
import husky.wooof.com.shared.MyFactory;
import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;

import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

public class ChatSidebar extends Composite {

	private static ChatSidebarUiBinder uiBinder = GWT
			.create(ChatSidebarUiBinder.class);

	interface ChatSidebarUiBinder extends UiBinder<Widget, ChatSidebar> {
	}

	@UiField Label txtChatStatus;
	@UiField HTMLPanel chatMessagePanel;
	@UiField HuskyTextArea txtChatMessage;
	
	private Channel channel;
	private HuskyUser user;
	private HuskyCard card;
	private HuskyCardNavigation huskyCardNavigation;
	
	public ChatSidebar(HuskyCardNavigation huskyCardNavigation, HuskyUser user, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		this.user = user;
		this.card = card;
		initChannel();
	}
	
	private void initChannel(){
		append("Joining...");
        channel = new Channel(String.valueOf(card.getId()));
        channel.addChannelListener(new ChannelListener() {

            @Override
            public void onMessage(String message) {
                MyFactory myFactory = GWT.create(MyFactory.class);
                AutoBean<HuskyChatMessage> bean = AutoBeanCodex.decode(myFactory, HuskyChatMessage.class,message);
                HuskyChatMessage huskyMessage = bean.as();
                if(huskyMessage.getMessage().equals(IHuskyConstants.CHAT_JOINED)){
                	onAddActiveUser(huskyMessage);
                }else{
                	onDisplayMessage(huskyMessage);
                }
                
            }

            @Override
            public void onOpen() {
            	channel.send(user.getId() + ";" + IHuskyConstants.CHAT_JOINED);
                append("Joined!");
            }

            @Override
            public void onError(int code, String description) {
                append("Error! " + code + ". " + description);
            }

            @Override
            public void onClose() {
                append("Close!");
            }
            
        });
        channel.join();
       
	}

	@UiHandler("txtChatMessage")
	void onEnterMessage(KeyDownEvent e){
		if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER && !txtChatMessage.getText().isEmpty()){
			if (channel == null) {
	            append("Not connected");
	            return;
	        }

	        String message = txtChatMessage.getText();
	        if (message.isEmpty()) return;

	        txtChatMessage.setText("");
	        append("Sending message: " + message);
	        channel.send(user.getId() + ";" + message);
		}
	}
	
	private void onDisplayMessage(HuskyChatMessage chatMessage){
		chatMessagePanel.add(new ChatMessageItem(chatMessage));
		chatMessagePanel.getElement().setScrollTop(10000);
	}
	
	private void onAddActiveUser(HuskyChatMessage huskyMessage){
		//TODO Add Presence of all connected users
	}
	
	public void append(String line) {
       
    }
}
