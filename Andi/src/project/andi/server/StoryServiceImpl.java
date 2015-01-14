package project.andi.server;

import java.util.ArrayList;
import java.util.List;

import project.andi.client.services.StoryService;
import project.andi.shared.Story;
import project.andi.shared.StoryItem;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;

public class StoryServiceImpl extends RemoteServiceServlet implements StoryService  {

	private static final long serialVersionUID = 1L;
	private Objectify ofy = OfyService.ofy();
	
	@Override
	public void createStory(Story story) throws Exception {
		ofy.put(story);
	}

	@Override
	public Story getStory(String code) throws Exception {
		Story story = ofy.query(Story.class).filter("code", code).get();
		if(story!=null){
			story.setViews(story.getViews()+1);
			ofy.put(story);
		}
		return story;
	}

	@Override
	public void createStoryItem(StoryItem item) throws Exception {
		ofy.put(item);
	}

	@Override
	public List<StoryItem> getAllStoryItems(Long storyId) throws Exception {
		List<StoryItem> items = new ArrayList<StoryItem>();
		for (StoryItem item : ofy.query(StoryItem.class).filter("storyId", storyId).order("creationDate")) {
			items.add(item);
		}
		return items;
	}

	@Override
	public List<Story> getAllStory() throws Exception {
		List<Story> items = new ArrayList<Story>();
		for (Story item : ofy.query(Story.class).order("creationDate")) {
			items.add(item);
		}
		return items;
	}

	@Override
	public Story login(String code, String password) throws Exception {
		Story story = ofy.query(Story.class).filter("code", code).filter("password", password).get();
		return story ;
	}

}
