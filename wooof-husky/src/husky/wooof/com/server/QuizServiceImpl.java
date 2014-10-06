package husky.wooof.com.server;

import husky.wooof.com.client.services.QuizService;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizItem;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class QuizServiceImpl extends RemoteServiceServlet implements QuizService {

	private static final long serialVersionUID = 1L;

	private Objectify ofy = OfyService.ofy();

	@Override
	public void saveQuiz(HuskyQuiz quiz, List<HuskyQuizItem> items) throws Exception {
		ofy.put(quiz);
		for(HuskyQuizItem item : items){
			item.setQuizId(quiz.getId());
			ofy.put(item);
		}
	}

	@Override
	public List<HuskyQuiz> getAllCardQuiz(HuskyCard card) throws Exception {
		List<HuskyQuiz> quizes = new ArrayList<>();
		for (HuskyQuiz l : ofy.query(HuskyQuiz.class).filter("cardId", card.getId()).order("creationDate")) {
			quizes.add(l);
		}

		return quizes;
	}

	@Override
	public void updateQuiz(HuskyQuiz quiz) throws Exception {
		ofy.put(quiz);
	}

	@Override
	public void deleteQuiz(HuskyQuiz quiz) throws Exception {
		ofy.delete(quiz);
	}

}
