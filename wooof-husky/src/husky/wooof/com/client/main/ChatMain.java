package husky.wooof.com.client.main;

import husky.wooof.com.shared.HuskyUser;
import husky.wooof.com.shared.MyFactory;
import husky.wooof.com.shared.MyMessage;
import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

public class ChatMain extends Composite {

	private static ChatMainUiBinder uiBinder = GWT
			.create(ChatMainUiBinder.class);

	interface ChatMainUiBinder extends UiBinder<Widget, ChatMain> {
	}

	@UiField TextBox txtMessage;
	@UiField TextArea areaChat;
	@UiField Button btnSend;
	
	private Channel channel;
	private HuskyUser user;
	
	public ChatMain(HuskyUser user) {
		initWidget(uiBinder.createAndBindUi(this));
		this.user = user;
		initChannel();
	}
	
	private void initChannel(){
		append("Joining...");

        channel = new Channel("test");
        channel.addChannelListener(new ChannelListener() {

            @Override
            public void onMessage(String message) {
                MyFactory myFactory = GWT.create(MyFactory.class);
                AutoBean<MyMessage> bean = AutoBeanCodex.decode(myFactory, MyMessage.class,message);
                MyMessage myMessage = bean.as();

                append("Message: " +  myMessage.getMessage());
            }

            @Override
            public void onOpen() {
                append("Joined!");
            }

            @Override
            public void onError(int code, String description) {
                append("Error! " + code + ". " + description);
            }

            @Override
            public void onClose() {
                append("Close!");
            }
        });
        channel.join();
	}

	@UiHandler("btnSend")
	void onSendMessage(ClickEvent e){
		if (channel == null) {
            append("Not connected");
            return;
        }

        String message = txtMessage.getText();
        if (message.isEmpty()) return;

        txtMessage.setText("");
        append("Sending message: " + message);
        channel.send(user.getEmail() + message);
	}
	
	public void append(String line) {
        String text = areaChat.getText();
        areaChat.setText(text.length() > 0 ? text + '\n' + line : line);
    }
	
}
