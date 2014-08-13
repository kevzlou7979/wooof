package husky.wooof.com.server;

import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyLesson;
import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.UploadedImage;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
    static {
        ObjectifyService.register(HuskyUser.class);
        ObjectifyService.register(HuskyCard.class);
        ObjectifyService.register(UploadedImage.class);
        ObjectifyService.register(HuskyLesson.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();//prior to v.4.0 use .begin() , 
                                        //since v.4.0  use ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

}
