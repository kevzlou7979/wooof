package husky.wooof.com.server;

import husky.wooof.com.client.services.CardService;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyItem;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class CardServiceImpl extends RemoteServiceServlet implements CardService {

	private static final long serialVersionUID = 1L;

	private Objectify ofy = OfyService.ofy();

	@Override
	public HuskyCard saveCard(HuskyCard card, List<HuskyUser> users) throws Exception {
		ofy.put(card);
		List<Key<HuskyUser>> cardUsers = new ArrayList<Key<HuskyUser>>();
		for (HuskyUser user : users) {
			cardUsers.add(new Key<HuskyUser>(HuskyUser.class, user.getId()));
			addUserToCard(user, getCard(card.getId()), IHuskyConstants.CARD_ADMIN, true);
		}
		card.setAdmins(cardUsers);
		
		
		return getCard(card.getId());
	}

	@Override
	public HuskyCard updateCard(HuskyCard card) throws Exception {
		ofy.put(card);
		return getCard(card.getId());
	}

	@Override
	public HuskyCard getCard(Long cardId) throws Exception {
		return ofy.get(HuskyCard.class, cardId);
	}

	@Override
	public void deleteCard(HuskyCard card) throws Exception {
		ofy.delete(card);
	}

	@Override
	public List<HuskyCard> getAllCards(HuskyUser user) throws Exception {
		List<HuskyCard> cards = new ArrayList<HuskyCard>();
		for (HuskyCard card : ofy.query(HuskyCard.class).filter("admins", user)) {
			cards.add(card);
		}
		for (HuskyCard card : ofy.query(HuskyCard.class).filter("viewers", user)) {
			if (!cards.contains(card)) {
				cards.add(card);
			}
		}
		return cards;
	}

	@Override
	public void addUserToCard(HuskyUser user, HuskyCard card, String type, boolean isActive) throws Exception {
		Key<HuskyUser> toBeAdd = new Key<HuskyUser>(HuskyUser.class, user.getId());
		switch (type) {
		case IHuskyConstants.CARD_ADMIN:
			card.getAdmins().add(toBeAdd);
			break;
		case IHuskyConstants.CARD_VIEWER:
			card.getViewers().add(toBeAdd);
		default:
			break;
		}
		HuskyUserCard userCard = new HuskyUserCard(card.getId(), user.getId(), isActive);
		ofy.put(userCard);
		ofy.put(card);
	}

	@Override
	public List<HuskyUser> getAllCardAdmins(HuskyCard card) throws Exception {
		List<HuskyUser> users = new ArrayList<HuskyUser>();
		for (Key<HuskyUser> user : getCard(card.getId()).getAdmins()) {
			users.add(ofy.get(user));
		}
		return users;
	}

	@Override
	public List<HuskyUser> getAllCardViewers(HuskyCard card) throws Exception {
		List<HuskyUser> users = new ArrayList<HuskyUser>();
		for (Key<HuskyUser> user : getCard(card.getId()).getViewers()) {
			users.add(ofy.get(user));
		}
		return users;
	}

	@Override
	public List<HuskyChatMessage> getAllChatMessage(HuskyCard card, int x) throws Exception {

		List<HuskyChatMessage> tempMessages = new ArrayList<HuskyChatMessage>();
		Query<HuskyChatMessage> chats = ofy.query(HuskyChatMessage.class).filter("cardId", card.getId()).order("creationDate");
		for (HuskyChatMessage chat : chats) {
			tempMessages.add(chat);
		}
		

		return tempMessages;
	}

	@Override
	public List<HuskyUserCard> getAllActiveUser(HuskyCard card) throws Exception {
		List<HuskyUserCard> userCards = new ArrayList<HuskyUserCard>();
		for (HuskyUserCard userCard : ofy.query(HuskyUserCard.class).filter("cardId", card.getId()).filter("active", true)) {
			userCards.add(userCard);
		}
		return userCards;
	}

	@Override
	public HuskyUserCard saveNewChat(HuskyUser user, HuskyCard card, boolean isSeen) throws Exception {

		HuskyUserCard userCard = getUserCard(user, card);
		int newChats = userCard.getNumNewChat();
		if (isSeen) {
			newChats = 0;
		}
		else {
			newChats++;
		}
		userCard.setNumNewChat(newChats);
		ofy.put(userCard);
		return userCard;
	}

	@Override
	public void onJoinCard(HuskyUser user, HuskyCard card) throws Exception {
		HuskyUserCard userCard = getUserCard(user, card);
		userCard.setActive(true);
		ofy.put(userCard);
	}

	@Override
	public void onLeaveCard(HuskyUser user, HuskyCard card) throws Exception {
		HuskyUserCard userCard = getUserCard(user, card);
		userCard.setActive(false);
		ofy.put(userCard);
	}

	@Override
	public void removeUserFromCard(HuskyUser user, HuskyCard card)
			throws Exception {
		HuskyUserCard userCard = getUserCard(user, card);
		ofy.delete(userCard);
		List<Key<HuskyUser>> tobeSaved = new ArrayList<Key<HuskyUser>>();
		for(Key<HuskyUser> u : card.getAdmins()){
			if(user.getId() != u.getId()){
				tobeSaved.add(u);
			}
			card.setAdmins(tobeSaved);
		}
		ofy.put(card);
	}
	
	private HuskyUserCard getUserCard(HuskyUser user, HuskyCard card){
		HuskyUserCard userCard = ofy.query(HuskyUserCard.class).filter("userId", user.getId()).filter("cardId", card.getId()).get();
		return userCard;
	}

	@Override
	public List<HuskyItem> getAllItems(HuskyCard card) throws Exception {
		List<HuskyItem> items = new ArrayList<HuskyItem>();
		for (HuskyItem item : ofy.query(HuskyItem.class).filter("cardId", card.getId()).order("creationDate")) {
			items.add(item);
		}
		return items;
	}

	@Override
	public HuskyItem getHuskyItemById(Long itemId) throws Exception {
		HuskyItem item = ofy.query(HuskyItem.class).filter("id", itemId).get();
		return item;
	}

}
