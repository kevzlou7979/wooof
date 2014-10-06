package husky.wooof.com.client.ui;

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
	@UiField Image btnDelete;
	
	private String text;
	private String name;
	
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

}
