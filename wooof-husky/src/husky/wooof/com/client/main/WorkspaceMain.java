package husky.wooof.com.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceMain extends Composite{

	private static WorkspaceMainUiBinder uiBinder = GWT
			.create(WorkspaceMainUiBinder.class);

	interface WorkspaceMainUiBinder extends UiBinder<Widget, WorkspaceMain> {
	}

	public WorkspaceMain() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
