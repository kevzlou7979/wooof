package husky.wooof.com.server;

import husky.wooof.com.client.services.CardService;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyUser;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

public class CardServiceImpl extends RemoteServiceServlet implements CardService{

	private static final long serialVersionUID = 1L;

	Objectify ofy = OfyService.ofy();
	
	@Override
	public HuskyCard saveCard(HuskyCard card, List<HuskyUser> users) throws Exception {
		List<Key<HuskyUser>> cardUsers = new ArrayList<Key<HuskyUser>>();
		for(HuskyUser user : users){
			cardUsers.add(new Key<HuskyUser>(HuskyUser.class, user.getId()));
		}
		card.setUsers(cardUsers);
		ofy.put(card);
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
		for(HuskyCard card : ofy.query(HuskyCard.class).filter("users", user)){
			cards.add(card);
		}
		return cards;
	}

}