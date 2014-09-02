package husky.wooof.com.server;

import husky.wooof.com.client.services.LessonService;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyLesson;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class LessonServiceImpl extends RemoteServiceServlet implements LessonService {

	private static final long serialVersionUID = 1L;

	private Objectify ofy = OfyService.ofy();

	@Override
	public void saveLesson(HuskyLesson lesson) throws Exception {
		ofy.put(lesson);
	}

	@Override
	public List<HuskyLesson> getAllCardLessons(HuskyCard card) throws Exception {
		List<HuskyLesson> lessons = new ArrayList<>();
		for (HuskyLesson l : ofy.query(HuskyLesson.class).filter("cardId", card.getId()).order("creationDate")) {
			lessons.add(l);
		}

		return lessons;
	}

	@Override
	public void updateLesson(HuskyLesson lesson) throws Exception {
		ofy.put(lesson);
	}

	@Override
	public void deleteLesson(HuskyLesson lesson) throws Exception {
		ofy.delete(lesson);
	}

}
