package husky.wooof.com.client.services;

import husky.wooof.com.shared.UploadedImage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

@RemoteServiceRelativePath("blob")
public interface BlobService extends RemoteService {
	public static class Connect {

		private static BlobServiceAsync service;

		public static BlobServiceAsync getService() {
			if (service == null) {
				service = (BlobServiceAsync) GWT.create(BlobService.class);
				PhonegapUtil.prepareService((ServiceDefTarget) service, "http://wooof-husky.appspot.com", "blob");
			}

			return service;
		}
	}

	public String getBlobstoreUploadUrl() throws Exception;

	public UploadedImage getUploadImage(String key) throws Exception;
}
