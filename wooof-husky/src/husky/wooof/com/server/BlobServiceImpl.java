package husky.wooof.com.server;

import husky.wooof.com.client.services.BlobService;
import husky.wooof.com.shared.UploadedImage;
import husky.wooof.com.shared.UploadedImageDao;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class BlobServiceImpl extends RemoteServiceServlet implements BlobService{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getBlobstoreUploadUrl() throws Exception{
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        return blobstoreService.createUploadUrl("/upload");
	}

	@Override
	public UploadedImage getUploadImage(String key) throws Exception{
		UploadedImageDao dao = new UploadedImageDao();
		UploadedImage image = dao.get(key);
		return image;
	}
}
