package project.andi.server;

import project.andi.client.services.StoryService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class StoryServiceImpl extends RemoteServiceServlet implements StoryService  {

	private static final long serialVersionUID = 1L;
	private Objectify ofy = OfyService.ofy();

}
