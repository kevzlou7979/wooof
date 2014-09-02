package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyLesson;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LessonServiceAsync {

	public void saveLesson(HuskyLesson lesson, AsyncCallback<Void> callback);

	public void getAllCardLessons(HuskyCard card, AsyncCallback<List<HuskyLesson>> callback);

	public void updateLesson(HuskyLesson lesson, AsyncCallback<Void> callback);

	public void deleteLesson(HuskyLesson lesson, AsyncCallback<Void> callback);

}
