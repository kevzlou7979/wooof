package project.andi.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import project.andi.shared.Story;
import project.andi.shared.StoryItem;

public interface StoryServiceAsync {

	void createStory(Story story, AsyncCallback<Void> callback);
	void getStory(String code, AsyncCallback<Story> callback);
	void createStoryItem(StoryItem item, AsyncCallback<Void> callback);
	void getAllStory(AsyncCallback<List<Story>> callback);
	void getAllStoryItems(Long storyId, AsyncCallback<List<StoryItem>> callback);
}
