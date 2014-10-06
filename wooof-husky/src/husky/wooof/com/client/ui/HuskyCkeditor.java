package husky.wooof.com.client.ui;

import husky.wooof.com.shared.IHuskyConstants;

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
	private String type;
	private Composite composite;
	
	public HuskyCkeditor(HTMLPanel tobeAppliedPanel, String type, Composite composite) {
		initWidget(uiBinder.createAndBindUi(this));
		this.type = type;
		this.composite = composite;
		ck = new CKEditor(CKConfig.full);
		panel.add(ck);
		this.tobeAppliedPanel = tobeAppliedPanel;
		ck.setHTML(tobeAppliedPanel.getElement().toString());
	}
	
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
		if(type!=null){
			switch (type) {
			case IHuskyConstants.CK_QUIZ_ITEM_TITLE:
				((QuizItem)composite).getQuizItem().setTitle(ck.getHTML());
				break;
			case IHuskyConstants.CK_QUIZ_ITEM_EXPLANATION:
				((QuizItem)composite).getQuizItem().setExplanation(ck.getHTML());
				break;
	
			default:
				break;
			}
		}
	}
	
	

	

}
