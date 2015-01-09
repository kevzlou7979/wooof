package project.andi.client.material;

import com.google.gwt.user.client.ui.HTMLPanel;

public class AndiLoader {
	private static PreLoader preLoader = new PreLoader();

	public static void showLoading(boolean isShow, HTMLPanel container) {
		if (isShow) {
			container.add(preLoader);
		} else {
			preLoader.removeFromParent();
		}

	}

	public static void showLoading(boolean isShow) {
		if (!isShow) {
			preLoader.removeFromParent();
		}
	}
}
