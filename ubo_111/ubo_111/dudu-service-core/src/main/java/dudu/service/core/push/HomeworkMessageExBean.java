package dudu.service.core.push;

public class HomeworkMessageExBean extends HomeworkMessageBean {

	private static final long serialVersionUID = 1L;
	private long fromUserId;
	private String title;
	private String imageUrl;

	public HomeworkMessageExBean() {
		this.type = "shw";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}
}
