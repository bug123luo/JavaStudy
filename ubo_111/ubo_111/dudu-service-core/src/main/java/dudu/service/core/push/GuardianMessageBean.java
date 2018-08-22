package dudu.service.core.push;

public class GuardianMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3399496230130896563L;

	private String school;
	private String grade;
	private String clazz;
	private String content;
	private long fromUserId;
	private String t;

	public GuardianMessageBean() {
		this.type = "gtxt";
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
