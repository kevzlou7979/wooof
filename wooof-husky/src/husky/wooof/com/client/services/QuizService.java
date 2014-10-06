package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizItem;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("quiz")
public interface QuizService extends RemoteService {
	public static class Connect {

		private static QuizServiceAsync service;

		public static QuizServiceAsync getService() {
			if (service == null) {
				service = (QuizServiceAsync) GWT.create(QuizService.class);
				//PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "Quiz");
			}

			return service;
		}
	}

	public void saveQuiz(HuskyQuiz quiz, List<HuskyQuizItem> items) throws Exception;

	public List<HuskyQuiz> getAllCardQuiz(HuskyCard card) throws Exception;

	public void updateQuiz(HuskyQuiz lesson) throws Exception;

	public void deleteQuiz(HuskyQuiz lesson) throws Exception;

}
