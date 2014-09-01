 package husky.wooof.com.client.services;

import java.util.List;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CardServiceAsync {
	
	public void saveCard(HuskyCard card, List<HuskyUser> users, AsyncCallback<HuskyCard> callback);
	
	public void updateCard(HuskyCard card, AsyncCallback<HuskyCard> callback);
	
	public void getCard(Long cardId, AsyncCallback<HuskyCard> callback);
	
	public void deleteCard(HuskyCard card, AsyncCallback<Void> callback);

	public void getAllCards(HuskyUser userm, AsyncCallback<List<HuskyCard>> callback);
	
	public void addUserToCard(HuskyUser user, HuskyCard card, String type, AsyncCallback<Void> callback);
	
	public void getAllCardAdmins(HuskyCard card, AsyncCallback<List<HuskyUser>> callback);
	
	public void getAllCardViewers(HuskyCard card, AsyncCallback<List<HuskyUser>> callback);
	
	public void getAllChatMessage(HuskyCard card, AsyncCallback<List<HuskyChatMessage>> callback);

	public void saveNewChat(HuskyUser user, HuskyCard card, boolean isSeen, AsyncCallback<HuskyUserCard> callback);
	
	public void getAllActiveUser(HuskyCard card, AsyncCallback<List<HuskyUserCard>> callback);

}
