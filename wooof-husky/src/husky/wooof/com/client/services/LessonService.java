package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyLesson;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("lesson")
public interface LessonService extends RemoteService {
	public static class Connect {

		private static LessonServiceAsync service;

		public static LessonServiceAsync getService() {
			if (service == null) {
				service = (LessonServiceAsync) GWT.create(LessonService.class);
			}

			return service;
		}
	}

	public void saveLesson(HuskyLesson lesson) throws Exception;

	public List<HuskyLesson> getAllCardLessons(HuskyCard card) throws Exception;

	public void updateLesson(HuskyLesson lesson) throws Exception;

	public void deleteLesson(HuskyLesson lesson) throws Exception;

}
