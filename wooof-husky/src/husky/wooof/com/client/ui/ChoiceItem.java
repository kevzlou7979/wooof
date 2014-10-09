package husky.wooof.com.client.ui;

import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class ChoiceItem extends Composite {

	private static ChoiceItemUiBinder uiBinder = GWT
			.create(ChoiceItemUiBinder.class);

	interface ChoiceItemUiBinder extends UiBinder<Widget, ChoiceItem> {
	}
	
	@UiField RadioButton rbItem;
	@UiField HTMLPanel choicePanel;
	@UiField Image btnDelete, btnEditChoice;

	
	private Composite com;
	private String text;
	private String name;
	private boolean value;
	
	public ChoiceItem() {
		initWidget(uiBinder.createAndBindUi(this));
		choicePanel.add(new Label("Choice Item"));
		btnDelete.removeFromParent();
	}
	
	public ChoiceItem(String name){
		initWidget(uiBinder.createAndBindUi(this));
		choicePanel.add(new Label("Choice Item"));
		rbItem.setName(name);
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz title", new HuskyCkeditor(choicePanel),60);
	}

	public String getText() {
		return text;
	}

	@UiHandler("rbItem")
	void onSelectChoice(ClickEvent e){
		if(com instanceof QuizItem){
			int index = ((QuizItem) com).getMultipleChoicePanel().getWidgetIndex(this);
			((HuskyQuizMultiplechoice) ((QuizItem) com).getQuizItem()).setCorrectAnswer(index);
		}
	}
	
	public void setText(String text) {
		this.text = text;
		rbItem.setText(text);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		rbItem.setName(name);
	}
	
	@UiHandler("btnEditChoice")
	void onEditChoice(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your Choice", new HuskyCkeditor(choicePanel),60);
	}
	
	@UiHandler("btnDelete")
	void onDeleteChoice(ClickEvent e){
		this.removeFromParent();
	}

	public HTMLPanel getChoicePanel() {
		return choicePanel;
	}

	public void setChoicePanel(HTMLPanel choicePanel) {
		this.choicePanel = choicePanel;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
		rbItem.setValue(value);
	}

	public Composite getCom() {
		return com;
	}

	public void setCom(Composite com) {
		this.com = com;
	}

	public RadioButton getRbItem() {
		return rbItem;
	}

	public void setRbItem(RadioButton rbItem) {
		this.rbItem = rbItem;
	}

	public Image getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Image btnDelete) {
		this.btnDelete = btnDelete;
	}

	public Image getBtnEditChoice() {
		return btnEditChoice;
	}

	public void setBtnEditChoice(Image btnEditChoice) {
		this.btnEditChoice = btnEditChoice;
	}
	
	

}
