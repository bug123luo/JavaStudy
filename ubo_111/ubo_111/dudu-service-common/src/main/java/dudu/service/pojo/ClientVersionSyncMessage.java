package dudu.service.pojo;
public class ClientVersionSyncMessage extends SimpleMessage {
	private ClientVersionSyncBody messageBody;

	public ClientVersionSyncBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ClientVersionSyncBody messageBody) {
		this.messageBody = messageBody;
	}
	
	
}
