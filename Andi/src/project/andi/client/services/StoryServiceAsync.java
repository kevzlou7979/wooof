package project.andi.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import project.andi.shared.Story;

public interface StoryServiceAsync {

	void createStory(Story story, AsyncCallback<Void> callback);
	
}
