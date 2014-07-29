package husky.wooof.com.server;

import husky.wooof.com.shared.UploadedImage;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

@SuppressWarnings("deprecation")
public class HuskyUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();    

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("image");
       
        if (blobKey == null) {

        } else {

        	ImagesService imagesService = ImagesServiceFactory.getImagesService();
        	String imageUrl = imagesService.getServingUrl(blobKey);
        	
        	//TODO Remove this part
        	imageUrl = imageUrl.replace("0.0.0.0", "127.0.0.1");
        	
        	Entity uploadedImage = new Entity("UploadedImage");
        	uploadedImage.setProperty("blobKey", blobKey);
        	uploadedImage.setProperty(UploadedImage.CREATED_AT, new Date());
        	uploadedImage.setUnindexedProperty(UploadedImage.SERVING_URL, imageUrl);
        	
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        	datastore.put(uploadedImage);
        	
        	String keyString = KeyFactory.keyToString(uploadedImage.getKey());
            res.sendRedirect("/upload?uploadedImageKey=" + keyString);            		
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	
    	String uploadedImageKey = req.getParameter("uploadedImageKey");
    	resp.setHeader("Content-Type", "text/html");
    	System.out.println(uploadedImageKey);
    	resp.getWriter().println(uploadedImageKey);
    	
    }
}
