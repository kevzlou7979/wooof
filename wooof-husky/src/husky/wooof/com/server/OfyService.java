package husky.wooof.com.server;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyChatMessage;
import husky.wooof.com.shared.HuskyImageLesson;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyLinkLesson;
import husky.wooof.com.shared.HuskyPlaceLesson;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizDefinition;
import husky.wooof.com.shared.HuskyQuizItem;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.HuskyQuizTrueFalse;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.HuskyUserCard;
import husky.wooof.com.shared.HuskyYoutubeLesson;
import husky.wooof.com.shared.UploadedImage;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	static {
		ObjectifyService.register(HuskyUser.class);
		ObjectifyService.register(HuskyCard.class);
		ObjectifyService.register(UploadedImage.class);
		ObjectifyService.register(HuskyChatMessage.class);
		ObjectifyService.register(HuskyLesson.class);
		ObjectifyService.register(HuskyUserCard.class);
		ObjectifyService.register(HuskyYoutubeLesson.class);
		ObjectifyService.register(HuskyImageLesson.class);
		ObjectifyService.register(HuskyPlaceLesson.class);
		ObjectifyService.register(HuskyLinkLesson.class);
		
		/*
		 * Quiz
		 */
		ObjectifyService.register(HuskyQuiz.class);
		ObjectifyService.register(HuskyQuizItem.class);
		ObjectifyService.register(HuskyQuizDefinition.class);
		ObjectifyService.register(HuskyQuizMultiplechoice.class);
		ObjectifyService.register(HuskyQuizTrueFalse.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.begin();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
