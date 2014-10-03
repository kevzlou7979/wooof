package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyQuiz;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface QuizServiceAsync {

	public void saveQuiz(HuskyQuiz quiz, AsyncCallback<Void> callback);

	public void getAllCardQuiz(HuskyCard card, AsyncCallback<List<HuskyQuiz>> callback);

	public void updateQuiz(HuskyQuiz quiz, AsyncCallback<Void> callback);

	public void deleteQuiz(HuskyQuiz quiz, AsyncCallback<Void> callback);

}
