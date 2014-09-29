package husky.wooof.com.client.dialog;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyDialog extends DialogBox {

	private static HuskyDialogUiBinder uiBinder = GWT.create(HuskyDialogUiBinder.class);

	interface HuskyDialogUiBinder extends UiBinder<Widget, HuskyDialog> {
	}

	@UiField
	HTMLPanel dialogPanel;

	public HuskyDialog(Widget composite, double top, double left, double width) {
		
		setWidget(uiBinder.createAndBindUi(this));
		this.getElement().getStyle().setWidth(width, Unit.PCT);
		this.getElement().getStyle().setTop(top, Unit.PCT);
		this.getElement().getStyle().setLeft(left, Unit.PCT);
		this.addStyleName(HuskyResources.INSTANCE.huskymobilecss().huskyDialog());
		dialogPanel.add(composite);
		this.setModal(true);
		this.setGlassEnabled(true);
		this.center();
		this.show();
	}
	
	@UiHandler("btnClose")
	void onCloseDialog(ClickEvent e) {
		hide();
	}

}
