package project.andi.client.material;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialDatePicker extends FocusPanel{

	private HTMLPanel panel;
	
	public MaterialDatePicker() {
		this.clear();
		initDatePicker();
		panel = new HTMLPanel("<input type='date' class='datepicker'>");
		this.add(panel);
	}
	
	public static native void initDatePicker()/*-{
		 $wnd.jQuery('.datepicker').pickadate();
	}-*/;
	
	
}
