package husky.wooof.com.client.sidebar;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.navigation.HuskyCardNavigation;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.services.CardService;
import husky.wooof.com.client.services.UserAccountService;
import husky.wooof.com.client.ui.ChatMessageItem;
import husky.wooof.com.client.ui.CircleImage;
import husky.wooof.com.client.ui.HuskyTextArea;
import husky.wooof.com.client.ui.NoResultUtil;
import husky.wooof.com.client.ui.NotificationManager;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyItem;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;
import husky.wooof.com.shared.IHuskyChatMessage;
import husky.wooof.com.shared.IHuskyConstants;
import husky.wooof.com.shared.MyFactory;

import java.util.List;

import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

@SuppressWarnings("deprecation")
public class ChatSidebar extends Composite {

	private static ChatSidebarUiBinder uiBinder = GWT.create(ChatSidebarUiBinder.class);

	interface ChatSidebarUiBinder extends UiBinder<Widget, ChatSidebar> {
	}

	@UiField
	Label txtChatStatus;
	@UiField
	HTMLPanel chatMessagePanel;
	@UiField
	ScrollPanel chatScrollPanel;
	@UiField
	HuskyTextArea txtChatMessage;

	private Channel channel;
	private ChannelListener channelListener;
	private HuskyUser user;
	private HuskyCard card;
	private HuskyCardNavigation huskyCardNavigation;
	private NoResultUtil util;
	int x = 0;

	public ChatSidebar(HuskyCardNavigation huskyCardNavigation, HuskyUser user, HuskyCard card) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyCardNavigation = huskyCardNavigation;
		this.user = user;
		this.card = card;
		txtChatStatus.setVisible(false);
		initChannel();
		displayAllJoinedUsers();
		/*chatScrollPanel.addScrollListener(new ScrollListener() {

			@Override
			public void onScroll(Widget widget, int scrollLeft, int scrollTop) {
				if (scrollTop == 0) {
					x = x + 10;
					getAllChatMessages(x);
				}
			}
		});*/
		onScrollDown();
		getAllChatMessages(x);
		onCloseBrowser();
	}

	private void initChannel() {
		channel = new Channel(String.valueOf(card.getId()));

		channelListener = new ChannelListener() {

			@Override
			public void onMessage(String message) {
				MyFactory myFactory = GWT.create(MyFactory.class);
				AutoBean<IHuskyChatMessage> bean = AutoBeanCodex.decode(myFactory, IHuskyChatMessage.class, message);
				IHuskyChatMessage huskyMessage = bean.as();
				if (huskyMessage.getMessage().equals(IHuskyConstants.CHAT_JOINED) || huskyMessage.getMessage().equals(IHuskyConstants.CHAT_LEAVE)) {
					displayAllJoinedUsers();
				}else if(huskyMessage.getMessage().equals(IHuskyConstants.REAL_BROWSE)){
					//TODO Real Browsing
					realBrowse(huskyMessage.getObjectId());
					
				}else {
					onDisplayMessage(huskyMessage);

					if (huskyCardNavigation.isCloseNav()) {
						onSaveNewChat(false);
					}
				}

			}

			private void realBrowse(Long objectId) {
				//EXECUTE THE REAL TIME EVENT FOR BROWSING
				CardService.Connect.getService().getHuskyItemById(objectId, new AsyncCallback<HuskyItem>() {
					
					@Override
					public void onSuccess(HuskyItem result) {
						huskyCardNavigation.getWorkspaceMain().executeLesson(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
				
			}

			@Override
			public void onOpen() {

				channel.send(user.getId() + ";" + IHuskyConstants.CHAT_JOINED);
				append("Joined!");
			}

			@Override
			public void onError(int code, String description) {
				Window.alert("CODE: " + String.valueOf(code) + " ," + description);
			}

			@Override
			public void onClose() {
				Window.alert("Channel closed");
			}

		};
		channel.join();
		channel.addChannelListener(channelListener);

	}

	private void onCloseBrowser() {
		Window.addWindowClosingHandler(new Window.ClosingHandler() {
			public void onWindowClosing(Window.ClosingEvent closingEvent) {
				closingEvent.setMessage("Do you really want to close the page?");
			}
		});
		Window.addCloseHandler(new CloseHandler<Window>() {

			@Override
			public void onClose(CloseEvent<Window> event) {
				onLeave();
			}
		});
	}

	@UiHandler("txtChatMessage")
	void onEnterMessage(KeyDownEvent e) {
		if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER && !txtChatMessage.getText().isEmpty()) {
			if (channel == null) {
				append("Not connected");
				return;
			}
			String message = txtChatMessage.getText();
			if (message.isEmpty())
				return;
			txtChatMessage.setText("");
			channel.send(user.getId() + ";" + message + ";" + card.getId());
			if(util!=null){
				util.removeFromParent();
			}
		}
	}

	private void onDisplayMessage(IHuskyChatMessage chatMessage) {
		chatMessagePanel.add(new ChatMessageItem(chatMessage, huskyCardNavigation));
		onScrollDown();
	}

	public void onScrollDown() {
		chatScrollPanel.setScrollPosition(chatScrollPanel.getMaximumVerticalScrollPosition());
	}

	private void displayAllJoinedUsers() {
		
		CardService.Connect.getService().getAllActiveUser(card, new AsyncCallback<List<HuskyUserCard>>() {

			@Override
			public void onSuccess(List<HuskyUserCard> result) {
				huskyCardNavigation.getWorkspaceMain().getActiveUsersPanel().clear();
				getUser(user.getId());
				for (HuskyUserCard userCard : result) {
					if (!userCard.getUserId().equals(user.getId())) {
						getUser(userCard.getUserId());
					}
					if (userCard.getUserId().equals(huskyCardNavigation.getWorkspaceMain().getHuskyMain().getUser().getId())) {
						if (userCard.getNumNewChat() > 0) {
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

	public void onSaveNewChat(final boolean isSeen) {
		CardService.Connect.getService().saveNewChat(user, card, isSeen, new AsyncCallback<HuskyUserCard>() {

			@Override
			public void onSuccess(HuskyUserCard result) {

				if (!isSeen) {
					huskyCardNavigation.getLblChatNum().setVisible(true);
					huskyCardNavigation.getLblChatNum().setText(String.valueOf(result.getNumNewChat()));
					NotificationManager.chatBeep();
				}
				else {
					huskyCardNavigation.getLblChatNum().setVisible(false);
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private void getUser(Long userId) {
		UserAccountService.Connect.getService().getUserById(userId, new AsyncCallback<HuskyUser>() {

			@Override
			public void onSuccess(HuskyUser result) {
				CircleImage image = new CircleImage();
				image.addStyleName(HuskyResources.INSTANCE.huskycss().activeUserItem());
				if (!result.getProfilePic().isEmpty()) {
					image.setImageProfile(result.getProfilePic());
				}
				else {
					image.setResource(HuskyResources.INSTANCE.ic_avatar());
				}
				image.setTitle(result.getEmail());
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

	private void getAllChatMessages(int x) {
		CardService.Connect.getService().getAllChatMessage(card, x, new AsyncCallback<List<HuskyChatMessage>>() {

			@Override
			public void onSuccess(List<HuskyChatMessage> result) {
				if(!result.isEmpty()){
					if (!result.isEmpty()) {
						for (HuskyChatMessage chatMessage : result) {
							onDisplayMessage((IHuskyChatMessage) chatMessage);
						}
						chatScrollPanel.setScrollPosition(400);
					}
					else {
						chatScrollPanel.setScrollPosition(0);
					}
				}else{
					util  = new NoResultUtil(HuskyResources.INSTANCE.ic_gray_chat(), "No Recent Chats", chatMessagePanel);
					util.getElement().getStyle().setLeft(65, Unit.PCT);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void onLeave(){
		CardService.Connect.getService().onLeaveCard(user, card, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				getChannel().send(user.getId() + ";" + IHuskyConstants.CHAT_LEAVE);
				RootPanel.get().clear();
				RootPanel.get().add(new HuskyMain(user));
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

	public HTMLPanel getChatMessagePanel() {
		return chatMessagePanel;
	}

	public void setChatMessagePanel(HTMLPanel chatMessagePanel) {
		this.chatMessagePanel = chatMessagePanel;
	}

}
