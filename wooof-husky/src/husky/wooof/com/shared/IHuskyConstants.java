package husky.wooof.com.shared;

public interface IHuskyConstants {

	public final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	public final String GOOGLE_CLIENT_ID = "674045101011-457aakispnhr59r7ksl90847qpbbas0j.apps.googleusercontent.com"; // available from the APIs console
	public final String GOOGLE_PLUS_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	public final String GOOGLE_MAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
	public final String GOOGLE_TOKEN = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";
	public final String API_KEY = "AIzaSyA5Et0lW5wJdjQm4J8z4o6qtIn8SP5o3E8";
	
	public final String FACEBOOK_AUTH_URL = "https://www.facebook.com/dialog/oauth"; 
	public final String FACEBOOK_CLIENT_ID = "650504511731140";
	public final String FACEBOOK_EMAIL_SCOPE = "email";
	public final String FACEBOOK_BIRTHDAY_SCOPE = "user_birthday";
	public final String FACEBOOK_TOKEN = "https://graph.facebook.com/me?access_token=";
	
	public final int NAV_CARDS = 0;
	public final int NAV_SETTINGS = 1;
	public final int NAV_TEAM = 2;
	public final int NAV_ACCOUNT = 3;
	public final int NAV_LOGOUT = 4;
	public final int NAV_LEAVE = 5;

	public final int NAV_CARD_INFO = 6;
	public final int NAV_CHAT = 7;
	public final int NAV_ADD_USER = 8;
	public final int NAV_NOTIFICATION = 9;

	public final int NAV_ADMIN = 10;
	public final int NAV_VIEWER = 11;

	public final int NAV_LESSON_INFO = 0;
	public final int NAV_LESSON_TYPE = 1;
	public final int NAV_LESSON_MATERIAL = 2;
	
	public final String LOADING_CIRCLE = "loadingCircle";
	public final String LOADING_SQUARE = "loadingSquare";

	public final String CARD_ADMIN = "Admin";
	public final String CARD_VIEWER = "Viewer";

	public final String LESSON_YOUTUBE = "youtube";
	public final String LESSON_IMAGE = "image";
	public final String LESSON_AUDIO = "audio";
	public final String LESSON_PLACE = "place";
	public final String LESSON_LINK = "link";

	public final String ACTION_CREATE = "create";

	public final String REGEX_MAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public final String MALE = "Male";
	public final String FEMALE = "Female";
	public final String CHAT_JOINED = "Joined";
	public final String CHAT_LEAVE = "Leave";
	public final String REAL_BROWSE = "RealBrowse";
	
	public final String ACTION_INFO = "Info";
	public final String ACTION_WARN = "Warn";
	public final String ACTION_ERROR = "Error";
	
	public final String QUIZ_MULTIPLE_CHOICE = "multipleChoice";
	public final String QUIZ_DEFINITION = "definition";
	public final String QUIZ_TRUE_FALSE = "trueFalse";
	
	public final String CK_QUIZ_TITLE = "ckQuizTitle";
	public final String CK_QUIZ_DESCRIPTION = "ckQuizDescription";
	public final String CK_QUIZ_ITEM_TITLE = "ckQuizItemTitle";
	public final String CK_QUIZ_ITEM_EXPLANATION = "ckQuizItemExplanation";
	
	public final String DELIMITER = ";;;";

}
