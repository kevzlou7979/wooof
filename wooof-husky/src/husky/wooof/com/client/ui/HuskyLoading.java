package husky.wooof.com.client.ui;

import husky.wooof.com.client.resources.HuskyResources;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class HuskyLoading {
	private static HTML html = new HTML();

	public static void showLoading(boolean isShow, HTMLPanel container, String message, int marginTop, String type) {
		if (isShow) {
			if (type.equals(IHuskyConstants.LOADING_CIRCLE)) {
				html.getElement().setAttribute("style", "position: relative; text-align: center;margin-top: " + marginTop + "%");
				html.setHTML("<div class='" + HuskyResources.INSTANCE.huskycss().loading() + "'><i></i><i></i><i></i></div><h2 class='" + HuskyResources.INSTANCE.huskycss().huskyLoadingText() + "'>" + message + "</h2>");
			}
			else if (type.equals(IHuskyConstants.LOADING_SQUARE)) {
				html.setHTML("<div class=" + HuskyResources.INSTANCE.huskycss().huskyLoadingSquare() + "></div>");
			}
			container.add(html);

		}
		else {
			html.removeFromParent();
		}

	}

	public static void showLoading(boolean isShow) {
		if (!isShow) {
			html.removeFromParent();
		}
	}

}
