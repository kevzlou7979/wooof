package freelance.nunobrito.client.i18n;

import com.google.gwt.core.client.GWT;

public interface LabelConstants extends com.google.gwt.i18n.client.Constants {

	public static LabelConstants lblCnst = (LabelConstants) GWT.create(LabelConstants.class);

	public final String COOKIE_EMAIL = "cookie_email";
	public final String LOCALE = "locale";
	public final String LOCALE_ENGLISH = "en";
	public final String LOCALE_GERMAN = "de";

	public final String AUTOMATIC = "Automatic";
	public final String MANUAL = "Manual";

	public final String ACTIVATE = "Activate";
	public final String DEACTIVATE = "Deactivate";
	
	public final String CSS_ERROR = "error";

	public final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public final String REGEX_DOUBLE = "[-+]?([0-9]*\\.[0-9]+|[0-9]+)";
	
	/* LOGIN PAGE */
	String Email();
	String Password();
	String RememberMe();
	String CreateAccount();
	String ForgotPassword();
	String Login();
	String English();
	String German();
	String GetInvolvedTheSuccess();
	String LimitedFreeShares();
	String DurableProfit();
	String UpToSixtyPercent();
	String WithOurSophisticated();
	String AffiliateOfTheLeading();
	String ParticipateAlready();
	String GetYourUniqueFree();
	String BecomeAMerchant();
	String YourAdvantage();
	String GetYourFree();
	String OtherSahres();
	String YouCanAtAnyTime();
	
	

}
