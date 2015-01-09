package project.andi.server;

import project.andi.client.services.StoryService;
import project.andi.shared.Story;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class StoryServiceImpl extends RemoteServiceServlet implements StoryService  {

	private static final long serialVersionUID = 1L;
	private Objectify ofy = OfyService.ofy();
	
	@Override
	public void createStory(Story story) throws Exception {
		ofy.put(story);
	}

}
