package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class HuskyLoading {
	private static HTML html = new HTML();
	
	public static void showLoading(boolean isShow, HTMLPanel container, String message, int marginTop) {
		if(isShow){
			html.getElement().setAttribute("style", "position: relative; text-align: center;margin-top: "+marginTop+"%"); 
			html.setHTML("<div class='"+HuskyResources.INSTANCE.huskyCSS().loading()
					+"'><i></i><i></i><i></i></div><h2 class='"
					+ HuskyResources.INSTANCE.huskyCSS().huskyLoadingText()+"'>"
					+ message+"</h2>");
			container.add(html);
		}else{
			html.removeFromParent();
		}
		
	}
	
	public static void showLoading(boolean isShow){
		if(!isShow){
			html.removeFromParent();
		}
	}

}
