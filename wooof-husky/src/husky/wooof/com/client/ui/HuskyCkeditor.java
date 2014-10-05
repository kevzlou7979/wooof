package husky.wooof.com.client.ui;

import com.axeiya.gwtckeditor.client.CKConfig;
import com.axeiya.gwtckeditor.client.CKEditor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class HuskyCkeditor extends Composite {

	private static HuskyCkeditorUiBinder uiBinder = GWT
			.create(HuskyCkeditorUiBinder.class);

	interface HuskyCkeditorUiBinder extends UiBinder<Widget, HuskyCkeditor> {
	}

	@UiField HTMLPanel panel;
	private CKEditor ck;
	private HTMLPanel tobeAppliedPanel;
	
	public HuskyCkeditor(HTMLPanel tobeAppliedPanel) {
		initWidget(uiBinder.createAndBindUi(this));
		ck = new CKEditor(CKConfig.full);
		panel.add(ck);
		this.tobeAppliedPanel = tobeAppliedPanel;
		ck.setHTML(tobeAppliedPanel.getElement().toString());
	}
	
	public void setSource(){
		tobeAppliedPanel.clear();
		tobeAppliedPanel.add(new HTMLPanel(ck.getHTML()));
	}
	
	

	

}
