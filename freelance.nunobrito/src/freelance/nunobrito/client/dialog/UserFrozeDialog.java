package freelance.nunobrito.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import freelance.nunobrito.client.services.UserService;
import freelance.nunobrito.shared.User;

public class UserFrozeDialog extends Composite {

	private static UserFrozeDialogUiBinder uiBinder = GWT
			.create(UserFrozeDialogUiBinder.class);

	interface UserFrozeDialogUiBinder extends UiBinder<Widget, UserFrozeDialog> {
	}

	private User user;
	
	public UserFrozeDialog(User user) {
		initWidget(uiBinder.createAndBindUi(this));
		this.user = user;
	}

	@UiHandler("lblLink")
	void onClickLink(ClickEvent e){
		user.setFreeze(false);
		UserService.Connect.getService().updateUser(user, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
