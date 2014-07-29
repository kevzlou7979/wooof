package husky.wooof.com.client.services;

import husky.wooof.com.shared.UploadedImage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("blob")
public interface BlobService extends RemoteService {
	public static class Connect {

		private static BlobServiceAsync service;

		public static BlobServiceAsync getService() {
			if (service == null) {
				service = (BlobServiceAsync) GWT.create(BlobService.class);
			}

			return service;
		}
	}
	
	public String getBlobstoreUploadUrl() throws Exception;
	
	public UploadedImage getUploadImage(String key) throws Exception;
}
