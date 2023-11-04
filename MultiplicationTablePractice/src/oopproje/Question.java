package oopproje;


public class Question implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Boolean bool;
	private String question;
	private String answer;
	private String time;
	
	public Question(String question,String answer) {
		this.answer=answer;this.question=question;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAnswer() {
		return answer;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	public String getQuestion() {
		return question;
	}

}
