package husky.wooof.com.client.ui;

import husky.wooof.com.client.dialog.ActionDialog;
import husky.wooof.com.client.dialog.HuskyDialog;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;

public class HuskyActionDialog {

	private static HuskyDialog dialog;
	private static ActionDialog actionDialog;
	
	public static void show(String type, String message, Composite com){
		actionDialog = new ActionDialog(type, message, com);
		dialog = new HuskyDialog(actionDialog, 10, 50, 30);
		actionDialog.setDialog(dialog);
		dialog.getElement().getStyle().setPadding(0, Unit.PCT);
	}
	
}
