 package husky.wooof.com.client.services;

import java.util.List;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CardServiceAsync {
	
	public void saveCard(HuskyCard card, List<HuskyUser> users, AsyncCallback<HuskyCard> callback);
	
	public void updateCard(HuskyCard card, AsyncCallback<HuskyCard> callback);
	
	public void getCard(Long cardId, AsyncCallback<HuskyCard> callback);
	
	public void deleteCard(HuskyCard card, AsyncCallback<Void> callback);

	public void getAllCards(HuskyUser userm, AsyncCallback<List<HuskyCard>> callback);
	
	public void getBlobstoreUploadUrl(AsyncCallback<String> callback);
}
