package husky.wooof.com.client.ui;

import com.bramosystems.oss.player.core.client.AbstractMediaPlayer;
import com.bramosystems.oss.player.core.client.PlayerUtil;
import com.bramosystems.oss.player.core.client.PluginNotFoundException;
import com.bramosystems.oss.player.core.client.PluginVersionException;
import com.bramosystems.oss.player.youtube.client.YouTubePlayer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class YoutubeVideo extends SimplePanel {

	private String url;
	private AbstractMediaPlayer player;

	public YoutubeVideo() {
		// TODO Auto-generated constructor stub
	}

	private void loadYoutubeVideo(String url) {

		try {
			player = new YouTubePlayer(url.replace("https://www.youtube.com/watch?v=", "").replace("http://www.youtube.com/watch?v=", ""), "100%", "100%");
			this.setWidget(player); // add player to panel.
		} catch (PluginVersionException e) {
			this.setWidget(new HTML(".. some nice message telling the " + "user to download plugin first .."));
		} catch (PluginNotFoundException e) {
			this.setWidget(PlayerUtil.getMissingPluginNotice(e.getPlugin()));
		} catch (Exception e) {
			Window.alert("Video Not Found");
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		loadYoutubeVideo(url);
	}

	public AbstractMediaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AbstractMediaPlayer player) {
		this.player = player;
	}

}
