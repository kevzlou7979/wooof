package husky.wooof.com.client.navigation;

import husky.wooof.com.client.HuskyMain;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.sidebar.AddUserSideBar;
import husky.wooof.com.client.sidebar.CardInfoSidebar;
import husky.wooof.com.client.sidebar.ChatSidebar;
import husky.wooof.com.client.sidebar.NotificationSidebar;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HuskyCardNavigation extends Composite {

	private static HuskyCardNavigationUiBinder uiBinder = GWT.create(HuskyCardNavigationUiBinder.class);

	interface HuskyCardNavigationUiBinder extends UiBinder<Widget, HuskyCardNavigation> {
	}

	@UiField
	HTMLPanel navMenu, navContent;
	@UiField
	Label btnHideNav, lblChatNum;
	@UiField
	Image navCardInfo, navChat, navAddUser, navNotification, navLeave;

	private CardInfoSidebar cardInfoSideBar;
	private ChatSidebar chatSideBar;
	private AddUserSideBar addUserSideBar;
	private NotificationSidebar notificationSidebar;
	private HuskyMain huskyMain;
	private WorkspaceMain workspaceMain;
	private boolean closeNav = true;

	public HuskyCardNavigation(WorkspaceMain workspaceMain) {
		initWidget(uiBinder.createAndBindUi(this));
		this.huskyMain = workspaceMain.getHuskyMain();
		this.workspaceMain = workspaceMain;
		huskyMain.setHuskyCardNavigation(this);
		cardInfoSideBar = new CardInfoSidebar(this);
		chatSideBar = new ChatSidebar(this, huskyMain.getUser(), workspaceMain.getCard());
		addUserSideBar = new AddUserSideBar(this);
		notificationSidebar = new NotificationSidebar(this);
		btnHideNav.setVisible(false);
		lblChatNum.setVisible(false);
	}

	@UiHandler("navCardInfo")
	void onNavCardInfo(ClickEvent e) {
		changeNav(navCardInfo, IHuskyConstants.NAV_CARD_INFO);
	}

	@UiHandler("navChat")
	void onNavChat(ClickEvent e) {
		changeNav(navChat, IHuskyConstants.NAV_CHAT);
	}

	@UiHandler("navAddUser")
	void onNavAddUser(ClickEvent e) {
		changeNav(navAddUser, IHuskyConstants.NAV_ADD_USER);
	}

	@UiHandler("navNotification")
	void onNavNotification(ClickEvent e) {
		changeNav(navNotification, IHuskyConstants.NAV_NOTIFICATION);
	}

	@UiHandler("btnHideNav")
	void onHideNav(ClickEvent e) {
		setCloseNav(true);
		navContent.setWidth("0px");
		btnHideNav.setVisible(false);
		setNavContentVisibility(false);
	}

	@UiHandler("navLeave")
	void onNavLeave(ClickEvent e) {
		changeNav(navLeave, IHuskyConstants.NAV_LEAVE);
	}

	private void setNavContentVisibility(boolean isVisible) {
		for (Widget w : navContent) {
			w.setVisible(isVisible);
		}
	}

	private void changeNav(Image menu, int navType) {
		for (Widget w : navMenu) {
			w.removeStyleName(HuskyResources.INSTANCE.huskycss().cardNavActive());
		}
		menu.addStyleName(HuskyResources.INSTANCE.huskycss().cardNavActive());
		navContent.setWidth("300px");
		btnHideNav.setVisible(true);
		setNavContentVisibility(true);
		navContent.clear();
		navContent.add(btnHideNav);
		switch (navType) {
		case IHuskyConstants.NAV_CARD_INFO:
			navContent.add(cardInfoSideBar);
			break;
		case IHuskyConstants.NAV_CHAT:

			setCloseNav(false);
			lblChatNum.setVisible(false);
			navContent.add(chatSideBar);
			chatSideBar.onSaveNewChat(true);
			chatSideBar.onScrollDown();

			break;
		case IHuskyConstants.NAV_ADD_USER:
			navContent.add(addUserSideBar);
			break;
		case IHuskyConstants.NAV_NOTIFICATION:
			navContent.add(notificationSidebar);
			break;
		case IHuskyConstants.NAV_LEAVE:
			chatSideBar.onLeave();
			break;
		default:
			break;
		}

	}

	public CardInfoSidebar getCardInfoSideBar() {
		return cardInfoSideBar;
	}

	public void setCardInfoSideBar(CardInfoSidebar cardInfoSideBar) {
		this.cardInfoSideBar = cardInfoSideBar;
	}

	public ChatSidebar getChatSideBar() {
		return chatSideBar;
	}

	public void setChatSideBar(ChatSidebar chatSideBar) {
		this.chatSideBar = chatSideBar;
	}

	public AddUserSideBar getAddUserSideBar() {
		return addUserSideBar;
	}

	public void setAddUserSideBar(AddUserSideBar addUserSideBar) {
		this.addUserSideBar = addUserSideBar;
	}

	public NotificationSidebar getNotificationSidebar() {
		return notificationSidebar;
	}

	public void setNotificationSidebar(NotificationSidebar notificationSidebar) {
		this.notificationSidebar = notificationSidebar;
	}

	public HuskyMain getHuskyMain() {
		return huskyMain;
	}

	public void setHuskyMain(HuskyMain huskyMain) {
		this.huskyMain = huskyMain;
	}

	public WorkspaceMain getWorkspaceMain() {
		return workspaceMain;
	}

	public void setWorkspaceMain(WorkspaceMain workspaceMain) {
		this.workspaceMain = workspaceMain;
	}

	public HTMLPanel getNavContent() {
		return navContent;
	}

	public void setNavContent(HTMLPanel navContent) {
		this.navContent = navContent;
	}

	public boolean isCloseNav() {
		return closeNav;
	}

	public void setCloseNav(boolean closeNav) {
		this.closeNav = closeNav;
	}

	public Label getLblChatNum() {
		return lblChatNum;
	}

	public void setLblChatNum(Label lblChatNum) {
		this.lblChatNum = lblChatNum;
	}

}
