package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class HuskyLoading {
	private static HTML html = new HTML();
	public static void showLoading(boolean isShow) {
		if(isShow){
			html.addStyleName(HuskyResources.INSTANCE.huskyCSS().loading());
			html.setHTML("<i></i><i></i><i></i>");
			RootPanel.get().add(html);
		}else{
			html.removeFromParent();
		}
		
	}

}
