package husky.wooof.com.client.services;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyLesson;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

@RemoteServiceRelativePath("lesson")
public interface LessonService extends RemoteService {
	public static class Connect {

		private static LessonServiceAsync service;

		public static LessonServiceAsync getService() {
			if (service == null) {
				service = (LessonServiceAsync) GWT.create(LessonService.class);
				PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "lesson");
			}

			return service;
		}
	}

	public void saveLesson(HuskyLesson lesson) throws Exception;

	public List<HuskyLesson> getAllCardLessons(HuskyCard card) throws Exception;

	public void updateLesson(HuskyLesson lesson) throws Exception;

	public void deleteLesson(HuskyLesson lesson) throws Exception;

}
