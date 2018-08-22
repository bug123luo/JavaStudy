package dudu.service.core.push;

/**
 * 短信推送Bean
 * @author Administrator
 *
 */
public class SMSMessageBean extends BasicMessageBean{

	private static final long serialVersionUID = 8625782419252938384L;
	private String sensorId;
	private String number;
	private String message;

	public SMSMessageBean() {
		super.setType("sms");
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
