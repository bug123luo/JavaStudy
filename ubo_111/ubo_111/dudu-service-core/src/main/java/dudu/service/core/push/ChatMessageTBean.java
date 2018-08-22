package dudu.service.core.push;


public class ChatMessageTBean extends BasicMessageBean {

	private static final long serialVersionUID = 1L;
	/**
	 * 消息唯一标识、主键值
	 */
	private String id;
	/**
	 * 该条消息的发送者ID
	 */
	private String fromUserId;
	/**
	 * 群内所有用户ID集，各userId以逗号分隔,用来标识一个群聊或单聊
	 */
	private String groupToken;
	/**
	 * 群类型：
	 * 		users	用户群
	 * 		cards	由卡关联的群
	 */
	private String groupType;
	/**
	 * 群内所有用户ID集或者友娃卡的SensorID，各ID以逗号分隔,用来标识一个群聊或单聊
	 */
	private String memberIds;
	/**
	 * 消息的类型：
	 * 			text	文本
	 *			media	多媒体文件
	 *			textMedia 文本和多媒体文件的混合体
	 */
	private String msgType;
	/**
	 * 文本内容
	 */
	private String textContent;
	/**
	 * 多媒体文件ID
	 */
	private String mediaId;
	/**
	 * 消息的发送时间,以消息到达API服务器的时间为准
	 */
	private long crtTime;
	


	public ChatMessageTBean() {
		this.type = "chatMessage";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getFromUserId() {
		return fromUserId;
	}


	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}


	public String getGroupToken() {
		return groupToken;
	}


	public void setGroupToken(String groupToken) {
		this.groupToken = groupToken;
	}


	public String getGroupType() {
		return groupType;
	}


	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}


	public String getMemberIds() {
		return memberIds;
	}


	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}


	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getTextContent() {
		return textContent;
	}


	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}


	public String getMediaId() {
		return mediaId;
	}


	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public long getCrtTime() {
		return crtTime;
	}


	public void setCrtTime(long crtTime) {
		this.crtTime = crtTime;
	}

	
	
}
