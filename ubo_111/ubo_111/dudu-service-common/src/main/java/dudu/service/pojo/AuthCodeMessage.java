package dudu.service.pojo;

public class AuthCodeMessage extends SimpleMessage{
	
	private AuthCodeMessageBody messageBody;

	public AuthCodeMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(AuthCodeMessageBody messageBody) {
		this.messageBody = messageBody;
	}
	
	
	
}
