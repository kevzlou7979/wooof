package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyItem;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("card")
public interface CardService extends RemoteService {

	public static class Connect {

		private static CardServiceAsync service;

		public static CardServiceAsync getService() {
			if (service == null) {
				service = (CardServiceAsync) GWT.create(CardService.class);
				//PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "card");
			}

			return service;
		}
	}

	public HuskyCard saveCard(HuskyCard card, List<HuskyUser> users) throws Exception;

	public HuskyCard updateCard(HuskyCard card) throws Exception;

	public HuskyCard getCard(Long cardId) throws Exception;

	public void deleteCard(HuskyCard card) throws Exception;

	public List<HuskyCard> getAllCards(HuskyUser user) throws Exception;

	public void addUserToCard(HuskyUser user, HuskyCard card, String type, boolean isActive) throws Exception;

	public void removeUserFromCard(HuskyUser user, HuskyCard card) throws Exception;
	
	public List<HuskyUser> getAllCardAdmins(HuskyCard card) throws Exception;

	public List<HuskyUser> getAllCardViewers(HuskyCard card) throws Exception;

	public List<HuskyChatMessage> getAllChatMessage(HuskyCard card, int x) throws Exception;

	public HuskyUserCard saveNewChat(HuskyUser user, HuskyCard card, boolean isSeen) throws Exception;

	public List<HuskyUserCard> getAllActiveUser(HuskyCard card) throws Exception;
	
	public void onJoinCard(HuskyUser user, HuskyCard card) throws Exception;
	
	public void onLeaveCard(HuskyUser user, HuskyCard card) throws Exception;
	
	public List<HuskyItem> getAllItems(HuskyCard card) throws Exception;

}
