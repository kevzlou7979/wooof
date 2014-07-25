package husky.wooof.com.shared;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.client.ui.HuskyMessage;
import husky.wooof.com.client.ui.HuskyPasswordBox;
import husky.wooof.com.client.ui.HuskyTextBox;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FieldVerifier {

	public static boolean isValidFields(HTMLPanel panel, HTMLPanel messageCon){
		boolean isValid = false;
		for(Widget w : panel){
			if(w instanceof HuskyTextBox || w instanceof HuskyPasswordBox){
				if(((TextBox)w).getText().isEmpty()){
					w.addStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBoxError());
					isValid = false;
					HuskyMessage.showMessage(false, messageCon, "Please provide empty fields.");
				}else{
					w.removeStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBoxError());
					isValid = true;
				}
			}
		}
		return isValid;
	}
	
	public static boolean isValidEmailFields(HuskyTextBox txtEmail, HTMLPanel messageCon){
		boolean isValid = false;
		if(!txtEmail.getText().matches(husky.wooof.com.client.resources.IHuskyConstants.REGEX_MAIL)){
			txtEmail.addStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBoxError());
			isValid = false;
			HuskyMessage.showMessage(false, messageCon, "Incorrect Email Address.");
		}else{
			txtEmail.removeStyleName(HuskyResources.INSTANCE.huskyCSS().huskyTextBoxError());
			isValid = true;
		}
		return isValid;
	}
	
	public static boolean isPasswordMatched(HTMLPanel messageCon, HuskyPasswordBox txtPass, HuskyPasswordBox txtPass2){
		boolean isValid = false;
		if(txtPass.getText().equals(txtPass2.getText())){
			isValid = true;
		}else{
			isValid = false;
			HuskyMessage.showMessage(false, messageCon, "Password didn't match.");
		}
		return isValid;
	}

}
