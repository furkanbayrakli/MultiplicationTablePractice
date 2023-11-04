package oopproje;

public class ScoreTable implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String examName;
	private int score;
	
	public ScoreTable(String name,String examName,int score) {
		this.examName=examName;this.score=score;this.name=name;
	}

	public String getName() {
		return name;
	}


	public String getExamName() {
		return examName;
	}


	public int getScore() {
		return score;
	}


	
	
}
