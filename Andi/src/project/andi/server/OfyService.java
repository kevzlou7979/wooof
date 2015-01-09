package project.andi.server;

import project.andi.shared.Story;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	static {
		ObjectifyService.register(Story.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.begin();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
