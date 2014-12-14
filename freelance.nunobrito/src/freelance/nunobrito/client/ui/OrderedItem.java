package freelance.nunobrito.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class OrderedItem extends Composite {

	private static OrderedItemUiBinder uiBinder = GWT
			.create(OrderedItemUiBinder.class);

	interface OrderedItemUiBinder extends UiBinder<Widget, OrderedItem> {
	}

	@UiField Label lblText;
	private String text;
	
	public OrderedItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public OrderedItem(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		lblText.setText(text);
	}

}
