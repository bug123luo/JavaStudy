package dudu.service.pojo;

public class ClientHeartBeatReplyMessage extends SimpleMessage {
	
	private ClientHeartBeatReplyBody messageBody;

	public ClientHeartBeatReplyBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ClientHeartBeatReplyBody messageBody) {
		this.messageBody = messageBody;
	}

}
