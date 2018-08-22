package dudu.service.core.terminal;

import java.text.SimpleDateFormat;

public class LowBatMessage extends BasicMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4812104391945081494L;
	private SimpleDateFormat sdf;

	public LowBatMessage() {
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	}
}
