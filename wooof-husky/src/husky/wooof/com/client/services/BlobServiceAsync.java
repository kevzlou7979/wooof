package husky.wooof.com.client.services;

import husky.wooof.com.shared.UploadedImage;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BlobServiceAsync {
	
	public void getBlobstoreUploadUrl(AsyncCallback<String> callback);

	public void getUploadImage(String key, AsyncCallback<UploadedImage> callback);
}
