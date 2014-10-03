package husky.wooof.com.client.ui;

import com.google.gwt.media.client.Audio;

public class NotificationManager {

	private static Audio audio;
	
	public static void chatBeep()
	{
		audio = Audio.createIfSupported();
		audio.setSrc("audio/chatBeep.mp3");
		audio.play();
	}
	
}
