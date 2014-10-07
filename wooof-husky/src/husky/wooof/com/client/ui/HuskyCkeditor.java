package husky.wooof.com.client.ui;

import husky.wooof.com.client.dialog.CreateQuiz;
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
	private HuskyTextArea textArea;
	private HTMLPanel tobeAppliedPanel;
	private String type;
	private Composite composite;
	private boolean isTextArea;
	
	public HuskyCkeditor(HTMLPanel tobeAppliedPanel, String type, Composite composite, boolean isTextArea) {
		initWidget(uiBinder.createAndBindUi(this));
		this.type = type;
		this.composite = composite;
		this.isTextArea = isTextArea;
		if(isTextArea){
			textArea = new HuskyTextArea();
			panel.add(textArea);
			if(type!=null){
				switch (type) {
				case IHuskyConstants.CK_QUIZ_TITLE:
					textArea.setText(((CreateQuiz)composite).getLblTitle().getText());
					break;
				case IHuskyConstants.CK_QUIZ_DESCRIPTION:
					textArea.setText(((CreateQuiz)composite).getLblDescription().getText());
				default:
					break;
				}
			}
		}else{
			ck = new CKEditor(CKConfig.full);
			ck.setHTML(tobeAppliedPanel.getElement().toString());
			panel.add(ck);
		}
		
		this.tobeAppliedPanel = tobeAppliedPanel;
	}
	
	public HuskyCkeditor(HTMLPanel tobeAppliedPanel) {
		initWidget(uiBinder.createAndBindUi(this));
		ck = new CKEditor(CKConfig.full);
		panel.add(ck);
		this.tobeAppliedPanel = tobeAppliedPanel;
		ck.setHTML(tobeAppliedPanel.getElement().toString());
	}
	
	
	public void setSource(){
		if(isTextArea){
			if(type!=null){
				switch (type) {
				case IHuskyConstants.CK_QUIZ_TITLE:
					((CreateQuiz)composite).getLblTitle().setText(textArea.getText());
					break;
				case IHuskyConstants.CK_QUIZ_DESCRIPTION:
					((CreateQuiz)composite).getLblDescription().setText(textArea.getText());
				
				default:
					break;
				}
			}
		}else{
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
	

	

}
