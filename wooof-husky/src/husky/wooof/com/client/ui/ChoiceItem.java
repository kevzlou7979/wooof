package husky.wooof.com.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class ChoiceItem extends Composite {

	private static ChoiceItemUiBinder uiBinder = GWT
			.create(ChoiceItemUiBinder.class);

	interface ChoiceItemUiBinder extends UiBinder<Widget, ChoiceItem> {
	}
	
	@UiField RadioButton rbItem;
	
	private String text;
	private String name;
	
	public ChoiceItem() {
		initWidget(uiBinder.createAndBindUi(this));
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

}
