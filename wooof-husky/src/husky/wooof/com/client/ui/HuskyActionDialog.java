package husky.wooof.com.client.ui;

import husky.wooof.com.client.dialog.ActionDialog;
import husky.wooof.com.client.dialog.HuskyDialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;

public class HuskyActionDialog {

	private static HuskyDialog dialog;
	private static ActionDialog actionDialog;
	
	public static void show(String type, String message, String title){
		actionDialog = new ActionDialog(type, message, title);
		dialog = new HuskyDialog(actionDialog, 10, 50, 30);
		actionDialog.setDialog(dialog);
		dialog.getElement().getStyle().setPadding(0, Unit.PCT);
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
		    public void execute() {
		    	dialog.center();
		    }
		});  
	}

	public static void show(String type, String message, String title,
			Composite com, int width) {
		
		actionDialog = new ActionDialog(type, message, title);
		actionDialog.setCom(com);
		dialog = new HuskyDialog(actionDialog, 1005, 50, width);
		actionDialog.setDialog(dialog);
		dialog.getElement().getStyle().setPadding(0, Unit.PCT);
		if(com instanceof HuskyCkeditor){
			actionDialog.getMainPanel().add(com);
		}
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
		    public void execute() {
		    	dialog.center();
		    }
		});  
	}
	
}
