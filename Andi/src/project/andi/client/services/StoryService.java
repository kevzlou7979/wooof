package project.andi.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("story")
public interface StoryService extends RemoteService{

	public static class Connect {

		private static StoryServiceAsync service;

		public static StoryServiceAsync getService() {
			if (service == null) {
				service = (StoryServiceAsync) GWT.create(StoryService.class);
				//PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "Story");
			}

			return service;
		}
	}
	
}
