package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.client.ui.ChatMessageItem;
import husky.wooof.com.client.ui.CircleImage;
import husky.wooof.com.client.ui.HuskyTextArea;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;
import husky.wooof.com.shared.IHuskyChatMessage;
import husky.wooof.com.shared.IHuskyConstants;
import husky.wooof.com.shared.MyFactory;

import java.util.List;

import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	private ChannelListener channelListener;
	private HuskyUser user;
	private HuskyCard card;
	private HuskyCardNavigation huskyCardNavigation;
	
	public ChatSidebar(HuskyCardNavigation huskyCardNavigation, HuskyUser user, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		this.user = user;
		this.card = card;
		txtChatStatus.setVisible(false);
		getAllChatMessages();
		initChannel();
	}
	
	private void initChannel(){
		append("Joining...");
        channel = new Channel(String.valueOf(card.getId()));
        channelListener = new ChannelListener() {

            @Override
            public void onMessage(String message) {
                MyFactory myFactory = GWT.create(MyFactory.class);
                AutoBean<IHuskyChatMessage> bean = AutoBeanCodex.decode(myFactory, IHuskyChatMessage.class,message);
                IHuskyChatMessage huskyMessage = bean.as();
                if(huskyMessage.getMessage().equals(IHuskyConstants.CHAT_JOINED) || huskyMessage.getMessage().equals(IHuskyConstants.CHAT_LEAVE)){
                	displayAllJoinedUsers();
                }else{
                	onDisplayMessage(huskyMessage);
                	
                	if(huskyCardNavigation.isCloseNav()){
            			onSaveNewChat(false);
                	}
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
            
        };
        channel.addChannelListener(channelListener);
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
	        channel.send(user.getId() + ";" + message + ";" + card.getId());
		}
	}
	
	private void onDisplayMessage(IHuskyChatMessage chatMessage){
		chatMessagePanel.add(new ChatMessageItem(chatMessage, huskyCardNavigation));
		chatMessagePanel.getElement().setScrollTop(5000);
	}
	
	private void displayAllJoinedUsers(){
		huskyCardNavigation.getWorkspaceMain().getActiveUsersPanel().clear();
		CardService.Connect.getService().getAllActiveUser(card, new AsyncCallback<List<HuskyUserCard>>() {
			
			@Override
			public void onSuccess(List<HuskyUserCard> result) {
				for(HuskyUserCard userCard : result){
					getUser(userCard.getUserId());
					if(userCard.getUserId().equals(huskyCardNavigation.getWorkspaceMain().getHuskyMain().getUser().getId())){
						if(userCard.getNumNewChat() > 0){
								huskyCardNavigation.getLblChatNum().setVisible(true);
								huskyCardNavigation.getLblChatNum().setText(String.valueOf(userCard.getNumNewChat()));
						}
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void onSaveNewChat(final boolean isSeen){
		CardService.Connect.getService().saveNewChat(user, card, isSeen, new AsyncCallback<HuskyUserCard>() {
			
			@Override
			public void onSuccess(HuskyUserCard result) {
				if(!isSeen){
					huskyCardNavigation.getLblChatNum().setVisible(true);
					huskyCardNavigation.getLblChatNum().setText(String.valueOf(result.getNumNewChat()));
				}else{
					huskyCardNavigation.getLblChatNum().setVisible(false);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	private void getUser(Long userId){
		UserAccountService.Connect.getService().getUserById(userId, new AsyncCallback<HuskyUser>() {
			
			@Override
			public void onSuccess(HuskyUser result) {
				CircleImage image = new CircleImage();
				image.addStyleName(HuskyResources.INSTANCE.huskycss().activeUserItem());
				image.setImageProfile(result.getProfilePic());
				huskyCardNavigation.getWorkspaceMain().getActiveUsersPanel().add(image);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
	}	
	
	public void append(String line) {
       
    }
	
	private void getAllChatMessages(){
		CardService.Connect.getService().getAllChatMessage(card, new AsyncCallback<List<HuskyChatMessage>>() {
			
			@Override
			public void onSuccess(List<HuskyChatMessage> result) {
				for(HuskyChatMessage chatMessage : result){
					onDisplayMessage((IHuskyChatMessage) chatMessage);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ChannelListener getChannelListener() {
		return channelListener;
	}

	public void setChannelListener(ChannelListener channelListener) {
		this.channelListener = channelListener;
	}
	
	
}
