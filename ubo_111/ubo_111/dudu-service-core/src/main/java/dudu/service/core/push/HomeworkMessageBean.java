package dudu.service.core.push;

public class HomeworkMessageBean extends BasicMessageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369830329423595388L;

	private String school;
	private String grade;
	private String clazz;
	private String mobile;
	private String content;
	private String t;

	public HomeworkMessageBean() {
		this.type = "hw";
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
